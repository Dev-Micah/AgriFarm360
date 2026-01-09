package org.micah.agrifarm360.ui.screens.expenses.presentation


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
import org.micah.agrifarm360.domain.models.Expense
import org.micah.agrifarm360.ui.components.CustomButton
import org.micah.agrifarm360.ui.components.CustomOutlinedTextField
import org.micah.agrifarm360.ui.components.SuccessDialog
import org.micah.agrifarm360.utils.getCurrentTimestamp

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun AddExpenseScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ExpensesViewModel = koinViewModel()
){
    var expenseName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var expenseNameError by remember { mutableStateOf(false) }
    var expenseAmountError by remember { mutableStateOf(false) }

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
                title = { Text("Add Expense") },
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
                value = expenseName,
                onValueChange = {
                    expenseName = it
                    expenseNameError = false
                },
                label = "Expense Name",
                isError = expenseNameError,
                supportingText = if (expenseNameError) "Name is required" else null
            )


            CustomOutlinedTextField(
                value = amount,
                onValueChange = {
                    amount = it
                    expenseAmountError = false
                },
                label = "Amount",
                isError = expenseAmountError,
                supportingText = if (expenseAmountError) "Valid amount is required" else null
            )

            CustomOutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = "Description",
            )

            CustomButton(
                onClick = {
                    expenseNameError = expenseName.isBlank()
                    expenseAmountError = amount.toDoubleOrNull() == null

                    if (!expenseNameError && !expenseAmountError) {
                        val expense = Expense(
                            id = 0,
                            name = expenseName,
                            amount = amount.toDouble(),
                            date = getCurrentTimestamp(),
                            description = description
                        )
                        viewModel.addExpense(expense = expense)
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = "Add Expense",
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
