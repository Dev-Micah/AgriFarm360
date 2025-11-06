package org.micah.agrifarm360.features.tasks.data.local


data class Task(
    val id: Int,
    val name: String,
    val scheduleTime: Long,
    val completionState: String
)