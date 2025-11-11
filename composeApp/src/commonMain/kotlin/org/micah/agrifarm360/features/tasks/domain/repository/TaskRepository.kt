package org.micah.agrifarm360.features.tasks.domain.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.features.tasks.data.local.TaskEntity

interface TaskRepository {
    fun getAllHabits() : Flow<List<TaskEntity>>
    suspend fun insertTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
}