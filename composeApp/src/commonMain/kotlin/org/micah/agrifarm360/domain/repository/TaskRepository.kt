package org.micah.agrifarm360.domain.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.data.local.entities.TaskEntity

interface TaskRepository {
    fun getAllTasks() : Flow<List<TaskEntity>>
    suspend fun insertTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
}