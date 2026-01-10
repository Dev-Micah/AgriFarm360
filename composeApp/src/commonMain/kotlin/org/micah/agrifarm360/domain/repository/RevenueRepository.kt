package org.micah.agrifarm360.domain.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.domain.models.Revenue

interface RevenueRepository{
    suspend fun getAllRevenue(): Flow<List<Revenue>>

}