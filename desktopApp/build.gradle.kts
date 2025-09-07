plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose.multiplatform)
}

compose.desktop {
    application {
        mainClass = "com.schedule.app.desktop.MainKt"
        
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