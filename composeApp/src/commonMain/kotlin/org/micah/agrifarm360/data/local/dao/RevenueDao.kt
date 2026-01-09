package org.micah.agrifarm360.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import org.micah.agrifarm360.data.local.entities.RevenueEntity

@Dao
interface RevenueDao {
    @Query("SELECT * FROM revenue")
    suspend fun getAllRevenue(): List<RevenueEntity>


}