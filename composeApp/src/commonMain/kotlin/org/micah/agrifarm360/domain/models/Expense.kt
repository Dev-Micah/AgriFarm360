package org.micah.agrifarm360.domain.models

data class Expense(
    val name: String,
    val amount: Double,
    val date: Long,
    val description: String
)