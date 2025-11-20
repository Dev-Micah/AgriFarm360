package org.micah.agrifarm360.ui.screens.dashboard.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.add
import agrifarm360.composeapp.generated.resources.notifications
import agrifarm360.composeapp.generated.resources.profileicon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.micah.agrifarm360.features.tasks.presentation.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    //val taskViewModel: TaskViewModel =
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "AgriFarm360",
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.profileicon),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },

                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.notifications),
                            contentDescription = "notifications"
                        )
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 80.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(Res.drawable.add),
                    contentDescription = "Add Task",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            //Other Dashboard content

            AddTaskAlertDialog(
                showDialog = showDialog,
                onCancel = { showDialog = false },
                onSaveTask = { task ->
                    println("Task saved: $task")
                    showDialog = false
                }
            )
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
                            if (taskText.isNotBlank()) {
                                onSaveTask(taskText.trim())
                                taskText = ""
                                onCancel()
                            }
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