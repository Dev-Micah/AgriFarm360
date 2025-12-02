package org.micah.agrifarm360.data.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.data.local.entities.TaskEntity
import org.micah.agrifarm360.data.local.dao.TaskDao
import org.micah.agrifarm360.domain.repository.TaskRepository

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {


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