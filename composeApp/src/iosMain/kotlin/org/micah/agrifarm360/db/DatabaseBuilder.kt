package org.micah.agrifarm360.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.micah.agrifarm360.data.local.Agrifarm360Database
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


fun getDatabaseBuilder(): RoomDatabase.Builder<Agrifarm360Database> {
    val dbFilePath = documentDirectory() + "/agrifarm_database.db"
    return Room.databaseBuilder<Agrifarm360Database>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}

