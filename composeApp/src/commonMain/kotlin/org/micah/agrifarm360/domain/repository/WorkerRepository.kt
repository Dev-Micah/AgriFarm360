package org.micah.agrifarm360.domain.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.domain.models.Worker

interface WorkerRepository {
    suspend fun addWorker(worker: Worker)
    suspend fun deleteWorker(worker: Worker)
    fun getAllWorkers(): Flow<List<Worker>>

}