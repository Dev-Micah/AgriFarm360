package org.micah.agrifarm360.ui.screens.expenses.presentation

import androidx.lifecycle.ViewModel
import org.micah.agrifarm360.domain.models.Expense
import org.micah.agrifarm360.domain.repository.ExpensesRepository

class ExpensesViewModel(expensesRepository: ExpensesRepository): ViewModel(){

}


data class ExpensesUiState(
    val isLoading: Boolean = false,
    val expenses: List<Expense> = emptyList(),
    val error: String? = null,
    val isSuccess: Boolean = false
)