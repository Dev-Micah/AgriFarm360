package org.micah.agrifarm360.features.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.micah.agrifarm360.features.tasks.data.local.TaskEntity
import org.micah.agrifarm360.features.tasks.domain.repository.TaskRepository

class TaskViewModel (private val taskRepository: TaskRepository): ViewModel(){

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState : StateFlow<TaskUiState> = _uiState

    init {
        loadTasks()
    }

    fun addTask(task: TaskEntity){
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

    fun loadTasks(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            taskRepository.getAllTasks()
                .onEach { tasks ->
                    _uiState.value = _uiState.value.copy(
                        habits = tasks,
                        isLoading = false
                    )
                }
                .launchIn(viewModelScope)
        }
    }

    fun markTaskComplete(){}


}


data class TaskUiState(
    val isLoading: Boolean = false,
    val habits: List<TaskEntity> = emptyList(),
    val error: String? = null
)