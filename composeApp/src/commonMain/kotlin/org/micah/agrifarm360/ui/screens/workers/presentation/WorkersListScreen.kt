package org.micah.agrifarm360.ui.screens.workers.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


data class Worker(
    val name: String,
    val phoneNumber: Int,
    val presence: String,
    val position: String
        )

val dummyWorkers = listOf(
    Worker("Alice Wambui", 711223344, "Present", "Farm Manager"),
    Worker("Brian Otieno", 722334455, "Absent", "Field Worker"),
    Worker("Cynthia Mwangi", 733445566, "Present", "Picker"),
    Worker("David Kiptoo", 744556677, "Late", "Tractor Driver"),
    Worker("Eunice Akinyi", 755667788, "Present", "Store Clerk")
)


@Composable
fun WorkersListScreen(workers: List<Worker>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(workers) { worker ->
            WorkerItem(worker)
        }
    }
}

@Composable
fun WorkerItem(worker: Worker){

}