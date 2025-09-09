package com.schedule.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.schedule.app.components.FloatingCreateButton
import com.schedule.app.components.QuickCreateDialog
import com.schedule.app.components.SimpleCalendarView
import com.schedule.app.model.TodoItem
import com.schedule.app.state.AppState
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("ScheduleApp")

@Composable
fun App() {
    // 调试日志 - 在调试模式下会显示详细信息
    logger.debug("App组件初始化开始")
    
    val appState = remember { AppState() }
    var showCreateDialog by remember { mutableStateOf(false) }
    
    // 调试信息 - 状态变化监控
    LaunchedEffect(appState.todos.value.size) {
        logger.debug("当前待办事项数量: {}", appState.todos.value.size)
    }
    
    LaunchedEffect(showCreateDialog) {
        logger.debug("创建对话框状态: {}", showCreateDialog)
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SimpleCalendarView(
            todos = appState.todos.value,
            selectedDate = appState.selectedDate.value,
            viewMode = appState.calendarViewMode.value,
            onDateSelected = { date -> appState.setSelectedDate(date) },
            onViewModeChanged = { mode -> appState.setCalendarViewMode(mode) },
            onDeleteTodo = { id -> appState.deleteTodo(id) },
            onEditTodo = { todo -> appState.updateTodo(todo) }
        )
    }
    
    if (showCreateDialog) {
        QuickCreateDialog(
            onDismiss = { showCreateDialog = false },
            onCreate = { todo ->
                appState.addTodo(todo)
            }
        )
    }
    
    FloatingCreateButton(
        onClick = { showCreateDialog = true }
    )
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Schedule App") {
        App()
    }
}