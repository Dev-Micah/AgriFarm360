package org.micah.agrifarm360.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.micah.agrifarm360.data.local.dao.WorkerDao
import org.micah.agrifarm360.data.mappers.toDomain
import org.micah.agrifarm360.data.mappers.toEntity
import org.micah.agrifarm360.domain.models.Worker
import org.micah.agrifarm360.domain.repository.WorkerRepository

class WorkersRepositoryImpl (
    private val workerDao: WorkerDao): WorkerRepository {
    override suspend fun addWorker(worker: Worker) {
        workerDao.insert(worker.toEntity())
    }

    override suspend fun deleteWorker(worker: Worker) {
        workerDao.delete(worker.toEntity())
    }

    override fun getAllWorkers(): Flow<List<Worker>> {
        return workerDao.getAllWorkers()
            .map { workers ->
                workers.map { it.toDomain() }

            }
    }


}