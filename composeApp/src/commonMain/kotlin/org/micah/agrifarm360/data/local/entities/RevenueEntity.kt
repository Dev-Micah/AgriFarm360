package org.micah.agrifarm360.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "revenue")
data class RevenueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val source: String,
    val date: Long
)
