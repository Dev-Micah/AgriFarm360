package org.micah.agrifarm360.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.micah.agrifarm360.data.local.dao.TaskDao
import org.micah.agrifarm360.data.local.dao.WorkerDao
import org.micah.agrifarm360.data.local.entities.TaskEntity
import org.micah.agrifarm360.data.local.entities.WorkerEntity

@Database(entities =
    [TaskEntity::class , WorkerEntity::class], version = 2, exportSchema = false)
@ConstructedBy(Agrifarm360DatabaseConstructor::class)
abstract class Agrifarm360Database: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun workerDao(): WorkerDao
//    abstract fun expenseDao(): ExpenseDao
//    abstract fun revenueDao(): RevenueDao

}


@Suppress("KotlinNoActualForExpect")
expect object Agrifarm360DatabaseConstructor : RoomDatabaseConstructor<Agrifarm360Database> {
    override fun initialize(): Agrifarm360Database
}

fun getAgrifarm360Database(
    builder: RoomDatabase.Builder<Agrifarm360Database>
): Agrifarm360Database {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        //.fallbackToDestructiveMigration()
        .build()
}