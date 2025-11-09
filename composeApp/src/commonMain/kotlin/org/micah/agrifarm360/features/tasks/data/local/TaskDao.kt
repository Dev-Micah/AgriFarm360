package org.micah.agrifarm360.features.tasks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(item: TaskEntity)

    @Delete
    suspend fun deleteHabit(task: TaskEntity)

    @Query("SELECT * FROM tasks ORDER BY id ASC ")
    fun allHabits (): Flow<List<TaskEntity>>


}