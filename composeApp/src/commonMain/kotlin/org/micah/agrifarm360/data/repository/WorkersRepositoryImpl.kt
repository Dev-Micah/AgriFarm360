package org.micah.agrifarm360.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.micah.agrifarm360.data.local.dao.WorkerDao
import org.micah.agrifarm360.data.local.entities.WorkerEntity
import org.micah.agrifarm360.data.mappers.toDomain
import org.micah.agrifarm360.domain.models.Worker
import org.micah.agrifarm360.domain.repository.WorkerRepository

class WorkersRepositoryImpl (private val workerDao: WorkerDao): WorkerRepository {
    override suspend fun addWorker(worker: WorkerEntity) {
       workerDao.insert(worker)
    }

    override suspend fun updateWorker(worker: WorkerEntity) {
        workerDao.updateWorker(worker)
    }

    override fun getAllWorkers(): Flow<List<Worker>> {
        return workerDao.getAllWorkers().map{
            it.map { it.toDomain() }
        }
    }


    override suspend fun deleteWorker(worker: WorkerEntity) {
        workerDao.delete(worker)
    }
}