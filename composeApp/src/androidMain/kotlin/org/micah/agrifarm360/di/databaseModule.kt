package org.micah.agrifarm360.di


import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.micah.agrifarm360.data.local.Agrifarm360Database

actual val platformModule = module {
    single<Agrifarm360Database> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = Agrifarm360Database::class.java,
            name = "agrifarm_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
