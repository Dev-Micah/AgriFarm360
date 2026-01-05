package org.micah.agrifarm360.ui.screens.expenses.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.add
import agrifarm360.composeapp.generated.resources.expanddropdown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.micah.agrifarm360.ui.components.ItemShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    navController: NavController,
    viewModel: ExpensesViewModel = koinViewModel()
){

    val uiState by viewModel.uiState.collectAsState()
    Scaffold (
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(text = "Farm Expenses",
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            )
                    }
                )

                Row(
                    modifier = Modifier.padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    OutlinedButton(onClick = {}){
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Date Range")

                                Icon(
                                    painter = painterResource(Res.drawable.expanddropdown),
                                    contentDescription = "Expand"
                                )

                        }
                    }
//                    Spacer(Modifier.size(60.dp))
//                    Button(onClick = {}){
//                        Row(
//                            horizontalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            Text("Add Expense")
//                        }
//                    }
                }
            }


        },
        floatingActionButton = {
           FloatingActionButton(
               onClick = {},
               modifier = Modifier.padding(bottom = 80.dp),
               containerColor = MaterialTheme.colorScheme.primary
                ){
               Icon(
                   painter = painterResource(Res.drawable.add),
                    contentDescription = "Add worker",
                  tint = MaterialTheme.colorScheme.onPrimary

                )
            }
        }
    ){innerpadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            when{
                uiState.isLoading ->{
                    ItemShimmer()
                }
                uiState.error != null ->{
                    Text("Error loading expenses: ${uiState.error}")
                }
                uiState.expenses.isEmpty() ->{
                    EmptyExpensesScreen()
                }

                else ->{
                    ExpensesListScreen(expenses = uiState.expenses)
                }
            }
        }
    }
}

@Composable
fun EmptyExpensesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("No Expenses")
    }
}