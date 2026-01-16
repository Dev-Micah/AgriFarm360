package org.micah.agrifarm360.ui.screens.revenue.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.arrow_back_24px
import androidx.compose.foundation.clickable
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
import org.micah.agrifarm360.domain.models.Revenue
import org.micah.agrifarm360.ui.components.CustomButton
import org.micah.agrifarm360.ui.components.CustomOutlinedTextField
import org.micah.agrifarm360.ui.components.SuccessDialog
import org.micah.agrifarm360.utils.AppDatePickerDialog
import org.micah.agrifarm360.utils.timestampToDate

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun AddRevenueScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RevenueViewModel = koinViewModel()
) {
    var revenueSource by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var amount by remember { mutableStateOf("") }

    var sourceError by remember { mutableStateOf(false) }
    var dateError by remember { mutableStateOf(false) }
    var amountError by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()

    var showSuccessDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

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

    if (showDatePicker) {
        AppDatePickerDialog(
            onDismiss = { showDatePicker = false },
            onConfirm = { date ->
                selectedDate = date
                showDatePicker = false
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Revenue") },
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
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(24.dp)
        ) {
            CustomOutlinedTextField(
                value = revenueSource,
                onValueChange = {
                    revenueSource = it
                    sourceError = false
                },
                label = "Revenue Source",
                isError = sourceError,
                supportingText = if (sourceError) "Source is required" else null
            )

            CustomOutlinedTextField(
                value = selectedDate?.timestampToDate() ?: "",
                onValueChange = {},
                label = "Date",
                isError = dateError,
                readOnly = true,
                supportingText = if (dateError) "Date is required" else null,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(
                            painterResource(Res.drawable.arrow_back_24px),
                            contentDescription = "Select Date"
                        )
                    }
                },
                modifier = Modifier.clickable { showDatePicker = true }
            )

            CustomOutlinedTextField(
                value = amount,
                onValueChange = {
                    amount = it
                    amountError = false
                },
                label = "Amount",
                isError = amountError,
                supportingText = if (amountError) "Valid amount is required" else null
            )

            CustomButton(
                onClick = {
                    sourceError = revenueSource.isBlank()
                    dateError = selectedDate == null
                    amountError = amount.toDoubleOrNull() == null

                    if (!sourceError && !dateError && !amountError) {
                        val revenue = Revenue(
                            id = 0,
                            source = revenueSource,
                            amount = amount.toDouble(),
                            date = selectedDate!!
                        )
                        viewModel.addRevenue(revenue)
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = "Add Revenue"
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
