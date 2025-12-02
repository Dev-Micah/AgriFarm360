package org.micah.agrifarm360.data.mappers

import org.micah.agrifarm360.data.local.entities.WorkerEntity
import org.micah.agrifarm360.domain.models.Worker

fun WorkerEntity.toDomain(): Worker {
    return Worker(
        id = id,
        fullName = fullName,
        phone = phone,
        role = role,
        dailyWage = dailyWage
    )
}


fun Worker.toEntity(): WorkerEntity{
    return WorkerEntity(
        id = id,
        fullName = fullName,
        phone = phone,
        role = role,
        dailyWage = dailyWage
    )
}