package org.micah.agrifarm360.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.micah.agrifarm360.features.tasks.data.local.TaskDatabase


fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<TaskDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("task_database.db")
    return Room.databaseBuilder<TaskDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}