package org.micah.agrifarm360.features.tasks.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int =1,
    val name: String,
    val createdAt: String,
)