package com.schedule.app.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.schedule.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Schedule App"
    ) {
        App()
    }
}