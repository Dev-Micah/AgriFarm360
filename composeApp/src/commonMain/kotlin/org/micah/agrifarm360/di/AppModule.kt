package org.micah.agrifarm360.di

import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.micah.agrifarm360.features.tasks.data.local.TaskDatabase
import org.micah.agrifarm360.features.tasks.data.repository.TaskRepositoryImpl
import org.micah.agrifarm360.features.tasks.domain.repository.TaskRepository
import org.micah.agrifarm360.features.tasks.presentation.TaskViewModel

val taskDataSourceModule = module{
    single { get<TaskDatabase>().taskDao() }
    single <TaskRepository> { TaskRepositoryImpl(get()) }

    viewModelOf(::TaskViewModel)
}



fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)

        modules(taskDataSourceModule)
    }
}