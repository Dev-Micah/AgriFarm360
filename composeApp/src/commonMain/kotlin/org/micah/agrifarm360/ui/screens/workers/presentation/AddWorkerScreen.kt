package org.micah.agrifarm360.ui.screens.workers.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.arrow_back_24px
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.micah.agrifarm360.domain.models.Worker
import org.micah.agrifarm360.ui.components.CustomButton
import org.micah.agrifarm360.ui.components.CustomOutlinedTextField
import org.micah.agrifarm360.ui.components.SuccessDialog

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun AddWorkerScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: WorkerViewModel = koinViewModel()
){
    var workerName by remember { mutableStateOf("") }
    var workerPhone by remember { mutableStateOf("") }
    var workerRole by remember { mutableStateOf("") }
    var workerDailyWage by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var roleError by remember { mutableStateOf(false) }
    var wageError by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()

    var showSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            showSuccessDialog = true
            delay(3000)
            showSuccessDialog = false
            viewModel.resetSuccess()
            navController.popBackStack()
        }
    }

    if (showSuccessDialog) {
        SuccessDialog()
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Register Worker") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back_24px),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ){ innerpadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(24.dp)
        ){
            CustomOutlinedTextField(
                value = workerName,
                onValueChange = { 
                    workerName = it 
                    nameError = false
                },
                label = "Worker Name",
                isError = nameError,
                supportingText = if (nameError) "Name is required" else null
            )
            CustomOutlinedTextField(
                value = workerPhone,
                onValueChange = { 
                    workerPhone = it 
                    phoneError = false
                },
                label = "Worker Phone",
                isError = phoneError,
                supportingText = if (phoneError) "Phone is required" else null
            )
            CustomOutlinedTextField(
                value = workerRole,
                onValueChange = { 
                    workerRole = it 
                    roleError = false
                },
                label = "Worker Role",
                isError = roleError,
                supportingText = if (roleError) "Role is required" else null
            )
            CustomOutlinedTextField(
                value = workerDailyWage,
                onValueChange = { 
                    workerDailyWage = it 
                    wageError = false
                },
                label = "Worker Wage",
                isError = wageError,
                supportingText = if (wageError) "Valid wage is required" else null
            )

            CustomButton(
                onClick = {
                    nameError = workerName.isBlank()
                    phoneError = workerPhone.isBlank()
                    roleError = workerRole.isBlank()
                    wageError = workerDailyWage.toDoubleOrNull() == null

                    if (!nameError && !phoneError && !roleError && !wageError) {
                        val worker = Worker(
                            id = 0,
                            fullName = workerName,
                            phone = workerPhone,
                            role = workerRole,
                            dailyWage = workerDailyWage.toDoubleOrNull() ?: 0.0
                        )
                        viewModel.registerWorker(worker = worker)
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = "Add Worker",
            )

            if (uiState.error != null) {
                Text(
                    text = uiState.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}


