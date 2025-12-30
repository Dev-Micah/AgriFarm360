package org.micah.agrifarm360.ui.screens.workers.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.micah.agrifarm360.ui.components.CustomButton
import org.micah.agrifarm360.ui.components.CustomOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWorkerScreen(
    modifier: Modifier = Modifier,
    viewModel: WorkerViewModel = koinInject()
){
    val workerName = remember { mutableStateOf("") }
    val workerPhone = remember { mutableStateOf("") }
    val workerRole= remember { mutableStateOf("") }
    val workerDailyWage= remember{mutableStateOf("")}

    val uiState by viewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("Add WorkerScreen")}
            )
        }
    ){ innerpadding->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                //.verticalScroll(rememberScrollState())
        ){
            CustomOutlinedTextField(
                value = workerName.value,
                onValueChange = { workerName.value = it },
                label = "Worker Name"
            )
            CustomOutlinedTextField(
                value = workerPhone.value,
                onValueChange = { workerPhone.value = it },
                label = "Worker Phone"
            )
            CustomOutlinedTextField(
                value = workerRole.value,
                onValueChange = { workerRole.value = it },
                label = "Worker Role"
            )
            CustomOutlinedTextField(
                value = workerDailyWage.value,
                onValueChange = { workerDailyWage.value = it },
                label = "Worker Wage"
            )

            CustomButton(
                onClick = {},
                modifier = modifier,
                text = "Add Worker"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddWorkerScreenPreview(){
    AddWorkerScreen()
}