package org.micah.agrifarm360.domain.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.data.local.entities.WorkerEntity
import org.micah.agrifarm360.domain.models.Worker

interface WorkerRepository {
    suspend fun addWorker(worker: WorkerEntity)
    fun getAllWorkers(): Flow<List<Worker>>
    suspend fun deleteWorker(worker: WorkerEntity)
}