package org.micah.agrifarm360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.data.local.entities.WorkerEntity

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(worker: WorkerEntity)

    @Delete
    suspend fun delete(worker: WorkerEntity)

    @Query("SELECT * FROM workers ORDER BY fullName ASC")
    fun getAllWorkers(): Flow<List<WorkerEntity>>
}
