package org.micah.agrifarm360.data.local

import androidx.room.RoomDatabase
import org.micah.agrifarm360.features.tasks.data.local.TaskDao

abstract class Agrifarm360Database: RoomDatabase() {
    abstract fun taskDao(): TaskDao
//    abstract fun workerDao(): WorkerDao
//    abstract fun expenseDao(): ExpenseDao
//    abstract fun revenueDao(): RevenueDao

}