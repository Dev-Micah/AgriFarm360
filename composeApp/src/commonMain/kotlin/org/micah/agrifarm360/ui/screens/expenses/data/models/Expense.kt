package org.micah.agrifarm360.ui.screens.expenses.data.models

data class Expense(
    val name: String,
    val amount: Double,
    val category: String,
    val date: Long,
    val description: String
)