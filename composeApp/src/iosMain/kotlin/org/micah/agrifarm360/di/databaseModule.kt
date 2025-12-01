package org.micah.agrifarm360.di

import androidx.room.Room
import org.koin.dsl.module
import org.micah.agrifarm360.data.local.Agrifarm360Database
import org.micah.agrifarm360.data.local.getAgrifarm360Database
import platform.Foundation.NSHomeDirectory

actual val platformModule = module {
    single<Agrifarm360Database> {
        val dbFile = NSHomeDirectory() + "/agrifarm_database.db"
        val builder = Room.databaseBuilder<Agrifarm360Database>(
            name = dbFile,
        )
        getAgrifarm360Database(builder)
    }
}
