package org.micah.agrifarm360.ui.screens.workers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.micah.agrifarm360.data.local.entities.WorkerEntity
import org.micah.agrifarm360.domain.models.Worker
import org.micah.agrifarm360.domain.repository.WorkerRepository


class WorkerViewModel (private val workerRepository: WorkerRepository): ViewModel() {
    private val _uiState = MutableStateFlow(WorkersUiState())
    val uiState: StateFlow<WorkersUiState> = _uiState.asStateFlow()

    init {
        loadWorkers()
    }

    fun registerWorker(worker: Worker) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val workerEntity = WorkerEntity(
                    fullName = worker.fullName,
                    phone = worker.phone,
                    role = worker.role,
                    dailyWage = worker.dailyWage
                )
                workerRepository.addWorker(workerEntity)
                _uiState.value = _uiState.value.copy(
                    isSuccess = true,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun resetSuccess() {
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }

    fun updateWorker(worker: Worker) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                workerRepository.updateWorker(worker)
                _uiState.value = _uiState.value.copy(
                    isSuccess = true,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
    fun loadWorkers(){
        _uiState.value =_uiState.value.copy(isLoading = true)
        workerRepository.getAllWorkers()
            .onEach { workers ->
                _uiState.value= uiState.value.copy(
                    workers = workers,
                    isLoading = false
                )

            }
            .launchIn(viewModelScope)
    }


}
data class WorkersUiState(
    val isLoading: Boolean = false,
    val workers: List<Worker> = emptyList(),
    val error: String? = null,
    val isSuccess: Boolean = false
)