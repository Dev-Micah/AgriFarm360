package org.micah.agrifarm360.features.tasks.data.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.features.tasks.data.local.TaskDao
import org.micah.agrifarm360.features.tasks.data.local.TaskEntity
import org.micah.agrifarm360.features.tasks.domain.repository.TaskRepository

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository{


    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllHabits()
    }

    override suspend fun insertTask(task: TaskEntity) {
        return taskDao.insertTask(task= task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        return taskDao.deleteHabit(task = task)
    }
}