plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.compose.compiler)
}

kotlin {
    jvmToolchain(17)
}

compose.desktop {
    application {
        mainClass = "com.schedule.app.desktop.MainKt"
        
        // JVM参数配置
        jvmArgs(
            "-Xmx1024m",
            "-Dfile.encoding=UTF-8"
        )
        
        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
            )
            packageName = "ScheduleApp"
            packageVersion = "1.0.0"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
}

// 调试任务 - debug级别日志
tasks.register("debug", JavaExec::class) {
    group = "application"
    description = "运行应用程序（调试模式 - debug级别日志）"
    mainClass.set("com.schedule.app.desktop.MainKt")
    classpath = sourceSets["main"].runtimeClasspath
    
    // 调试模式的JVM参数
    jvmArgs(
        "-Xmx1024m",
        "-Dfile.encoding=UTF-8",
        "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006",
        "-Dlogback.configurationFile=logback.xml"
    )
    
    // 设置系统属性 - debug级别
    systemProperty("java.awt.headless", "false")
    systemProperty("compose.application.configure.swing.globals", "true")
    systemProperty("logback.root.level", "DEBUG")
}

// 运行任务 - info级别日志
tasks.register("runInfo", JavaExec::class) {
    group = "application"
    description = "运行应用程序（生产模式 - info级别日志）"
    mainClass.set("com.schedule.app.desktop.MainKt")
    classpath = sourceSets["main"].runtimeClasspath
    
    // 生产模式的JVM参数
    jvmArgs(
        "-Xmx1024m",
        "-Dfile.encoding=UTF-8",
        "-Dlogback.configurationFile=logback.xml"
    )
    
    // 设置系统属性 - info级别
    systemProperty("java.awt.headless", "false")
    systemProperty("compose.application.configure.swing.globals", "true")
    systemProperty("logback.root.level", "INFO")
}