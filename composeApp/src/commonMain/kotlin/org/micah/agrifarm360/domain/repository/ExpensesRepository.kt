package org.micah.agrifarm360.domain.repository

import kotlinx.coroutines.flow.Flow
import org.micah.agrifarm360.domain.models.Expense

interface ExpensesRepository {
    fun getAllExpenses(): Flow<List<Expense>>
    suspend fun addExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
    fun getTotalExpenses(): Flow<Double>
}
