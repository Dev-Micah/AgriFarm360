package org.micah.agrifarm360.features.tasks.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.add
import agrifarm360.composeapp.generated.resources.morevert
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.micah.agrifarm360.data.local.entities.TaskEntity
import org.micah.agrifarm360.ui.components.WorkerItemShimmer
import org.micah.agrifarm360.utils.timestampToDate
import org.micah.agrifarm360.utils.timestampToDate


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
                Icon(
                    painter = painterResource(Res.drawable.add),
                    contentDescription = "More",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
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
                ) {
                    items(uiState.tasks) { task ->
                        TaskScreenItem(task = task)
                    }
                }
            }
        }
    }

}

@Composable
fun TaskScreenItem(
    task: TaskEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .padding(start = 24.dp , end = 24.dp, bottom = 4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = task.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = task.createdAt.timestampToDate(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                        fontSize = 13.sp
                    )
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(Res.drawable.morevert),
                    contentDescription = "More"
                )
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskAlertDialog(
    showDialog: Boolean,
    onCancel: () -> Unit,
    onSaveTask: (String) -> Unit
) {
    var taskText by remember { mutableStateOf("") }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onCancel,
            title = { Text("Schedule a NewTask") },
            text = {
                OutlinedTextField(
                    value = taskText,
                    onValueChange = { taskText =it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    placeholder = {
                        Text(
                            "e.g Water the crops",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        disabledBorderColor = Color.Transparent
                    ),
                )

            },
            confirmButton = {
                Button(
                    onClick = {
                        onSaveTask(taskText)

                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = onCancel) {
                    Text("Cancel")
                }
            }
        )
    }

}