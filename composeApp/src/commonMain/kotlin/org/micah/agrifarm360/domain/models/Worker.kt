package org.micah.agrifarm360.domain.models

data class Worker(
    val id: Int,
    val fullName: String,
    val phone: String,
    val role: String,
    val dailyWage: Double
)