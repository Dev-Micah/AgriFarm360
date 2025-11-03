package org.micah.agrifarm360.ui.screens.workers.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.addworker
import agrifarm360.composeapp.generated.resources.searchicon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkersScreen(){
    var selectedFilter by remember { mutableStateOf("All Workers") }
    val workerPresence = listOf("All Workers","Attendance")
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "My Farm Workers",
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(Res.drawable.addworker),
                                contentDescription = "Add worker",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {Text("Search worker by name")},
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.searchicon),
                            contentDescription = "Search worker"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color(0xFFE6F4EA),
                        unfocusedContainerColor = Color(0xFFE6F4EA)
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding( horizontal = 16.dp).padding(top = 8.dp)
                ) {
                    workerPresence.forEach { filter ->
                        WorkerFilter(
                            text = filter,
                            isSelected = selectedFilter == filter,
                            onClick = { selectedFilter = filter }
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(top = 200.dp)
        ) {
            WorkersListScreen(dummyWorkers)
        }
    }
}

@Composable
fun WorkerFilter(
    text:String,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(50.dp),
        color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )



    }

}

@Composable
fun EmptyWorkersScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Row {
            Text(text = "Tap")
            Spacer(Modifier.size(4.dp))
            Icon(
                painter = painterResource(Res.drawable.addworker),
                contentDescription = "Add worker",
                tint = MaterialTheme.colorScheme.onSurfaceVariant

            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = "to register workers",
                color = MaterialTheme.colorScheme.onSurfaceVariant

                )
        }
    }
}

