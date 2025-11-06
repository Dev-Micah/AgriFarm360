package org.micah.agrifarm360.ui.screens.workers.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.edit
import agrifarm360.composeapp.generated.resources.phonecall
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource


data class Worker(
    val name: String,
    val phoneNumber: Int,
    val presence: String,
    val position: String
)

val dummyWorkers = listOf(
    Worker("John Muchiri", 711223344, "Present", "Farm Manager"),
    Worker("Brian Otieno", 722334455, "Absent", "Field Worker"),
    Worker("Sila Nyanducha", 733445566, "Present", "Picker"),
    Worker("Micah Nyabuto", 744556677, "Late", "Tractor Driver"),
    Worker("Eunice Akinyi", 755667788, "Present", "Store Clerk")
)


@Composable
fun WorkersListScreen(workers: List<Worker>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(workers) { worker ->
            WorkerItem(
                worker = worker,
                onCallClick = {},
                onEditClick = {}
            )
        }
    }
}

@Composable
fun WorkerItem(
    worker: Worker,
    onCallClick: () -> Unit,
    onEditClick: (Worker) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE6F4EA)),
            contentAlignment = Alignment.Center
        ) {
            val initials = worker.name
                .split(" ")
                .filter { it.isNotBlank() }
                .take(2)
                .joinToString("") { it.first().uppercase() }
                .ifEmpty { "?" }

            Text(
                text = initials,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF388E3C)
            )

        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = worker.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = worker.position,
                color = Color(0xFF4CAF50),
                fontSize = 14.sp
            )
        }

        IconButton(onClick = { onCallClick() }) {
            Icon(
                painter = painterResource(Res.drawable.phonecall),
                contentDescription = "Call Worker"
            )
        }

        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(Res.drawable.edit),
                contentDescription = "Edit Worker"
            )
        }
    }
}