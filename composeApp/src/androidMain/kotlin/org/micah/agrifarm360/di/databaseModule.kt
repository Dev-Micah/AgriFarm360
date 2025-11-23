package org.micah.agrifarm360.di


import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.micah.agrifarm360.features.tasks.data.local.TaskDatabase

actual val platformModule = module {
    single<TaskDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = TaskDatabase::class.java,
            name = "task_database.db"
        ).build()
    }
}
