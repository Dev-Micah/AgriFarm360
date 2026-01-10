package org.micah.agrifarm360.domain.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.domain.models.Revenue

interface RevenueRepository{

    fun getAllRevenue(): Flow<List<Revenue>>

    suspend fun addRevenue(revenue: Revenue)

    suspend fun updateRevenue(revenue: Revenue)

    suspend fun deleteRevenue(revenue: Revenue)

    fun getTotalRevenue(): Flow<Double>

}