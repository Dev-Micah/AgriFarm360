package org.micah.agrifarm360.di

import org.koin.dsl.module
import org.micah.agrifarm360.features.tasks.data.local.TaskDatabase
import org.micah.agrifarm360.features.tasks.data.repository.TaskRepositoryImpl
import org.micah.agrifarm360.features.tasks.domain.repository.TaskRepository

val appModule = module{
    single { get<TaskDatabase>().taskDao() }
    single <TaskRepository> { TaskRepositoryImpl(get()) }
}