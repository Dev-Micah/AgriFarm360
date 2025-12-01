package org.micah.agrifarm360.domain.models

data class Worker(
    val name: String,
    val phoneNumber: Int,
    val role: String,
    val attendance: String
)