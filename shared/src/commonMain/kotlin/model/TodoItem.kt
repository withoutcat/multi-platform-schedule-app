package com.schedule.app.model

@kotlinx.serialization.Serializable
data class TodoItem(
    val id: String = "",
    val title: String,
    val description: String = "",
    val createdAt: String = "",
    val dueDate: String? = null,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val category: String = "",
    val color: String = "#FF6B6B"
) {
    companion object {
        fun createQuickTodo(title: String): TodoItem {
            return TodoItem(title = title)
        }
    }
}

@kotlinx.serialization.Serializable
enum class Priority {
    LOW, MEDIUM, HIGH
}