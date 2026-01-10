
package org.micah.agrifarm360.data.mappers

import org.micah.agrifarm360.data.local.entities.RevenueEntity
import org.micah.agrifarm360.domain.models.Revenue

fun RevenueEntity.toDomain(): Revenue {
    return Revenue(
        id = id,
        amount = amount,
        source = source,
        date = date,
    )
}

fun Revenue.toEntity(): RevenueEntity {
    return RevenueEntity(
        id = id,
        amount = amount,
        source = source,
        date = date,
    )
}