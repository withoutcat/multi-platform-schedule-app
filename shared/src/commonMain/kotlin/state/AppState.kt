package com.schedule.app.state

import com.schedule.app.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppState {
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()
    
    private val _selectedDate = MutableStateFlow<String>("2024-01-01")
    val selectedDate: StateFlow<String> = _selectedDate.asStateFlow()
    
    private val _calendarViewMode = MutableStateFlow(CalendarViewMode.WEEK)
    val calendarViewMode: StateFlow<CalendarViewMode> = _calendarViewMode.asStateFlow()
    
    fun addTodo(todo: TodoItem) {
        _todos.value = _todos.value + todo
    }
    
    fun updateTodo(updatedTodo: TodoItem) {
        _todos.value = _todos.value.map { 
            if (it.id == updatedTodo.id) updatedTodo else it 
        }
    }
    
    fun deleteTodo(id: String) {
        _todos.value = _todos.value.filter { it.id != id }
    }
    
    fun updateTodoTitle(id: String, newTitle: String) {
        _todos.value = _todos.value.map { 
            if (it.id == id) it.copy(title = newTitle) else it 
        }
    }
    
    fun toggleTodoCompletion(id: String) {
        _todos.value = _todos.value.map { 
            if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it 
        }
    }
    
    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }
    
    fun setCalendarViewMode(mode: CalendarViewMode) {
        _calendarViewMode.value = mode
    }
}

enum class CalendarViewMode {
    DAY, WEEK, MONTH
}