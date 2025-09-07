package com.schedule.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schedule.app.model.TodoItem
import com.schedule.app.state.CalendarViewMode
import com.schedule.app.utils.SimpleDateUtils

@Composable
fun SimpleCalendarView(
    todos: List<TodoItem>,
    selectedDate: String,
    viewMode: CalendarViewMode,
    onDateSelected: (String) -> Unit,
    onViewModeChanged: (CalendarViewMode) -> Unit,
    onDeleteTodo: ((String) -> Unit)? = null,
    onEditTodo: ((TodoItem) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // 简单的日历头部
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Schedule App",
                style = MaterialTheme.typography.headlineSmall
            )
            
            Text(
                text = SimpleDateUtils.formatDate(selectedDate),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 简单的待办列表
        val dayTodos = todos.filter { SimpleDateUtils.isSameDay(it.createdAt, selectedDate) }
        
        if (dayTodos.isEmpty()) {
            Text(
                text = "今天没有待办事项",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Column {
                dayTodos.forEach { todo ->
                    SimpleTodoItemCard(
                        todo = todo,
                        onDelete = onDeleteTodo?.let { { it(todo.id) } },
                        onEdit = onEditTodo?.let { { it(todo) } }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun SimpleTodoItemCard(
    todo: TodoItem,
    onDelete: (() -> Unit)? = null,
    onEdit: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
            )
            
            if (todo.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            if (todo.dueDate != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "截止时间: ${SimpleDateUtils.formatTime(todo.dueDate ?: "")}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}