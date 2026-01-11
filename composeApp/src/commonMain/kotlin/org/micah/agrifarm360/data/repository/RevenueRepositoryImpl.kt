package org.micah.agrifarm360.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.micah.agrifarm360.data.local.dao.RevenueDao
import org.micah.agrifarm360.data.mappers.toDomain
import org.micah.agrifarm360.data.mappers.toEntity
import org.micah.agrifarm360.domain.models.Revenue
import org.micah.agrifarm360.domain.repository.RevenueRepository

class RevenueRepositoryImpl (
    private val revenueDao: RevenueDao
): RevenueRepository{

    override fun getAllRevenue(): Flow<List<Revenue>>{
        return revenueDao.getAllRevenue().map { entities ->
            entities.map { it.toDomain() }

        }
    }

    override suspend fun addRevenue(revenue: Revenue) {
        revenueDao.insertRevenue(revenue.toEntity())

    }

    override suspend fun updateRevenue(revenue: Revenue) {
        revenueDao.updateRevenue(revenue.toEntity())
    }

    override suspend fun deleteRevenue(revenue: Revenue) {
        revenueDao.deleteRevenue(revenue.toEntity())
    }

    override fun getTotalRevenue(): Flow<Double> {
        return revenueDao.getTotalRevenue().map { it ?: 0.0 }
    }
}