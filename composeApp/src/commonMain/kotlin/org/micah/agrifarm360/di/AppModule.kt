package org.micah.agrifarm360.di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.micah.agrifarm360.data.local.Agrifarm360Database
import org.micah.agrifarm360.features.tasks.data.repository.TaskRepositoryImpl
import org.micah.agrifarm360.features.tasks.domain.repository.TaskRepository
import org.micah.agrifarm360.features.tasks.presentation.TaskViewModel

val taskDataSourceModule = module{
    single { get<Agrifarm360Database>().taskDao() }
    single<TaskRepository>{ TaskRepositoryImpl(taskDao = get ()) }
    //viewModelOf(::TaskViewModel)
    viewModel { TaskViewModel(taskRepository = get()) }
}



fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)

        modules(
            platformModule,
            taskDataSourceModule
        )
    }
}