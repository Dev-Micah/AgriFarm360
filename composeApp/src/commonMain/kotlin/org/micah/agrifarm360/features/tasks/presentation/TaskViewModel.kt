package org.micah.agrifarm360.features.tasks.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.micah.agrifarm360.features.tasks.data.local.TaskEntity
import org.micah.agrifarm360.features.tasks.domain.repository.TaskRepository

class TaskViewModel (private val taskRepository: TaskRepository): ViewModel(){

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState : StateFlow<TaskUiState> = _uiState


    data class TaskUiState(
        val isLoading: Boolean = false,
        val habits: List<TaskEntity> = emptyList(),
        val error: String? = null
    )

}