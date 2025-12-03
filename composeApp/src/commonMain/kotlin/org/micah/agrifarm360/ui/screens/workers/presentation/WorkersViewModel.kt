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


class WorkerViewModel (private val repository: WorkerRepository): ViewModel() {
    private val _uiState = MutableStateFlow(WorkersUiState())
    val uiState: StateFlow<WorkersUiState> = _uiState.asStateFlow()

    init {
        loadWorkers()
    }

    fun registerWorker(worker: Worker) {
        viewModelScope.launch {
            try {
                val workerEntity = WorkerEntity(
                    fullName = worker.fullName,
                    phone = worker.phone,
                    role = worker.role,
                    dailyWage = worker.dailyWage
                )
                repository.addWorker(workerEntity)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }

    fun loadWorkers(){
        _uiState.value =_uiState.value.copy(isLoading = true)
        repository.getAllWorkers()
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
    val error: String? = null
)