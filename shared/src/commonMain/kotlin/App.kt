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

@Composable
fun App() {
    val appState = remember { AppState() }
    var showCreateDialog by remember { mutableStateOf(false) }
    
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