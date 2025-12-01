package org.micah.agrifarm360.features.tasks.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.koinInject
import org.micah.agrifarm360.ui.components.WorkerItemShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  TasksScreen(
    navController: NavController,
    viewModel: TaskViewModel = koinInject()
){
    val uiState by viewModel.uiState.collectAsState()
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Tasks")
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary
                ){
                Text("+")
            }
        }

    ){innerpadding->

        when {
            uiState.isLoading -> {
                repeat(3) { WorkerItemShimmer() }
            }

            uiState.error != null -> {
                Text("Error loading tasks: ${uiState.error}")
            }

            uiState.tasks.isEmpty() -> {
                EmptyTasksScreen()
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                        .padding(innerpadding),
                    //contentPadding = PaddingValues(16.dp),
                ) {
                    items(uiState.tasks) { task ->
                        TaskItem(task = task)
                    }
                }
            }
        }
    }

}
