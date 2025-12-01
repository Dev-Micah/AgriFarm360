package org.micah.agrifarm360.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.micah.agrifarm360.data.local.Agrifarm360Database


fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<Agrifarm360Database> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("agrifarm_database.db")
    return Room.databaseBuilder<Agrifarm360Database>(
        context = appContext,
        name = dbFile.absolutePath
    )
}