package org.micah.agrifarm360.di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.micah.agrifarm360.data.local.Agrifarm360Database
import org.micah.agrifarm360.data.repository.ExpensesRepositoryImpl
import org.micah.agrifarm360.data.repository.TaskRepositoryImpl
import org.micah.agrifarm360.data.repository.WorkersRepositoryImpl
import org.micah.agrifarm360.domain.repository.ExpensesRepository
import org.micah.agrifarm360.domain.repository.TaskRepository
import org.micah.agrifarm360.domain.repository.WorkerRepository
import org.micah.agrifarm360.features.tasks.presentation.TaskViewModel
import org.micah.agrifarm360.ui.screens.expenses.presentation.ExpensesViewModel
import org.micah.agrifarm360.ui.screens.workers.presentation.WorkerViewModel

val taskDataSourceModule = module{
    single { get<Agrifarm360Database>().taskDao()}
    single { get<Agrifarm360Database>().workerDao()}
    single { get<Agrifarm360Database>().expensesDao() }

    single<TaskRepository>{ TaskRepositoryImpl(taskDao = get ()) }
    single<WorkerRepository>{ WorkersRepositoryImpl(workerDao = get ()) }
    single<ExpensesRepository>{ ExpensesRepositoryImpl(expensesDao = get()) }

    viewModel { TaskViewModel(taskRepository = get()) }
    viewModel { WorkerViewModel(workerRepository = get()) }
    viewModel { ExpensesViewModel(expensesRepository = get()) }
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