package org.micah.agrifarm360.di

import androidx.room.Room
import org.koin.dsl.module
import org.micah.agrifarm360.features.tasks.data.local.TaskDatabase
import org.micah.agrifarm360.features.tasks.data.local.getTaskDatabase
import platform.Foundation.NSHomeDirectory

actual val platformModule = module {
    single<TaskDatabase> {
        val dbFile = NSHomeDirectory() + "/task_database.db"
        val builder = Room.databaseBuilder<TaskDatabase>(
            name = dbFile,
        )
        getTaskDatabase(builder)
    }
}
