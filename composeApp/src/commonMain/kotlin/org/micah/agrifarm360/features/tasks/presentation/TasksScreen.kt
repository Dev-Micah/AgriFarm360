package org.micah.agrifarm360.features.tasks.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.arrow_back_24px
import agrifarm360.composeapp.generated.resources.morevert
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.micah.agrifarm360.data.local.entities.TaskEntity
import org.micah.agrifarm360.ui.components.ItemShimmer
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
                },

                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}){
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back_24px),
                            contentDescription = "Back"
                        )

                    }
                }
            )

        },


    ){innerpadding->

        when {
            uiState.isLoading -> {
                repeat(3) { ItemShimmer() }
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


