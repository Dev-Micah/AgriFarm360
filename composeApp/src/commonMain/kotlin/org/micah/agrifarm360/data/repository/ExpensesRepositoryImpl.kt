package org.micah.agrifarm360.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.micah.agrifarm360.data.local.dao.ExpensesDao
import org.micah.agrifarm360.data.local.entities.ExpenseEntity
import org.micah.agrifarm360.data.mappers.toDomain
import org.micah.agrifarm360.data.mappers.toEntity
import org.micah.agrifarm360.domain.models.Expense
import org.micah.agrifarm360.domain.repository.ExpensesRepository

class ExpensesRepositoryImpl(
    private val expensesDao: ExpensesDao
) : ExpensesRepository {

    override fun getAllExpenses(): Flow<List<Expense>> {
        return expensesDao.getAllExpenses().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addExpense(expense: ExpenseEntity) {
        expensesDao.insertExpense(expense.toEntity())
    }

    override suspend fun updateExpense(expense: Expense) {
        expensesDao.updateExpense(expense.toEntity())
    }

    override suspend fun deleteExpense(expense: Expense) {
        expensesDao.deleteExpense(expense.toEntity())
    }

    override fun getTotalExpenses(): Flow<Double> {
        return expensesDao.getTotalExpenses().map { it ?: 0.0 }
    }
}
