package org.micah.agrifarm360.features.tasks.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.morevert
import agrifarm360.composeapp.generated.resources.view_all
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import org.micah.agrifarm360.ui.navigation.Tasks
import org.micah.agrifarm360.data.local.entities.TaskEntity
import org.micah.agrifarm360.ui.components.WorkerItemShimmer

@Composable
fun TasksSection(
    navController: NavController,
    viewModel: TaskViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Scheduled Tasks",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
            TextButton(onClick = {navController.navigate(Tasks)}) {
                Text(
                    text = "View All",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(Modifier.size(4.dp))
                Icon(
                    painter = painterResource(Res.drawable.view_all),
                    contentDescription = "View all"
                )
            }
        }

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
                    modifier = Modifier.fillMaxWidth(),
                    //contentPadding = PaddingValues(16.dp),
                ) {
                    items(uiState.tasks.take(5)) { task ->
                        TaskItem(task = task)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(
        task: TaskEntity,
        modifier: Modifier = Modifier
    ) {
    Card(
        modifier = modifier.fillMaxWidth()
            .padding(start = 24.dp , end = 24.dp, bottom = 4.dp),
        border = BorderStroke(
            width = 0.5.dp,
            color =MaterialTheme.colorScheme.onSurface.copy(
                alpha =0.1f
            )
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = task.name,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = task.createdAt,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    fontSize = 13.sp
                )
            )
        }

            IconButton(onClick = {}){
                Icon(
                    painter = painterResource(Res.drawable.morevert),
                    contentDescription = "More"
                )
            }
    }
}

}

@Composable
fun EmptyTasksScreen() {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "No tasks scheduled \n" +
                    "Tap + to add"
        )
    }
}
