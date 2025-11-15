package org.micah.agrifarm360.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.micah.agrifarm360.features.tasks.data.local.TaskDatabase
import org.micah.agrifarm360.features.tasks.data.repository.TaskRepositoryImpl
import org.micah.agrifarm360.features.tasks.domain.repository.TaskRepository

val taskDataSourceModule = module{
    single { get<TaskDatabase>().taskDao() }
    single <TaskRepository> { TaskRepositoryImpl(get()) }

    //viewModel { TaskViewModel(get()) }
}



fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)

        modules(taskDataSourceModule)
    }
}