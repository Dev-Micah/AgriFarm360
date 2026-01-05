package org.micah.agrifarm360.ui.screens.expenses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.micah.agrifarm360.domain.models.Expense
import org.micah.agrifarm360.domain.repository.ExpensesRepository

class ExpensesViewModel(private val expensesRepository: ExpensesRepository): ViewModel(){
    
    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState: StateFlow<ExpensesUiState> = _uiState.asStateFlow()
    
    init {
        loadExpenses()
        loadTotalExpenses()
    }
    
    fun addExpense(expense: Expense){
        viewModelScope.launch { 
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                expensesRepository.addExpense(expense)
                _uiState.value = _uiState.value.copy(
                    isSuccess = true,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    error= e.message ?: "Failed to add expense",
                    isLoading = false
                )
            }
        }
    }
    
    private fun loadExpenses(){
        expensesRepository.getAllExpenses()
            .onEach { expenses -> 
                _uiState.value= _uiState.value.copy(
                    expenses =expenses,
                    isLoading = false
                )
            }
            .launchIn(viewModelScope)
    }

    private fun loadTotalExpenses() {
        expensesRepository.getTotalExpenses()
            .onEach { total ->
                _uiState.value = _uiState.value.copy(
                    totalExpenses = total
                )
            }
            .launchIn(viewModelScope)
    }
    
    fun resetSuccess(){
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }
}

data class ExpensesUiState(
    val isLoading: Boolean = false,
    val expenses: List<Expense> = emptyList(),
    val totalExpenses: Double = 0.0,
    val error: String? = null,
    val isSuccess: Boolean = false
)
