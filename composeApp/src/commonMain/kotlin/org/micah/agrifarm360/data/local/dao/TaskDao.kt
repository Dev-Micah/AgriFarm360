package org.micah.agrifarm360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.data.local.entities.TaskEntity

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteHabit(task: TaskEntity)

    @Query("SELECT * FROM tasks ORDER BY id ASC ")
    fun getAllHabits (): Flow<List<TaskEntity>>


}