package org.micah.agrifarm360.domain.models

data class Expense(
    val name: String,
    val amount: Double,
    val category: String,
    val date: Long,
    val description: String
)