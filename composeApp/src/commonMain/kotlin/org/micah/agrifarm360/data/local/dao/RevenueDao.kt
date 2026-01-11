package org.micah.agrifarm360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.data.local.entities.RevenueEntity

@Dao
interface RevenueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRevenue(revenue: RevenueEntity)

    @Update
    suspend fun updateRevenue(revenue: RevenueEntity)

    @Delete
    suspend fun deleteRevenue(revenue: RevenueEntity)

    @Query("SELECT * FROM revenue")
     fun getAllRevenue(): Flow<List<RevenueEntity>>

    @Query("SELECT SUM(amount) FROM revenue")
    fun getTotalRevenue(): Flow<Double?>

}