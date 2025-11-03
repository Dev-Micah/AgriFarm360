package org.micah.agrifarm360.ui.screens.expenses.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.expanddropdown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(){

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
                    OutlinedButton(onClick = {}){
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Category")
                                Icon(
                                    painter = painterResource(Res.drawable.expanddropdown),
                                    contentDescription = "Expand",
                                )

                        }
                    }
                }
            }


        }
    ){
        Column(
            modifier = Modifier.padding(top = 150.dp)
        ) {
            ExpensesListScreen(
                dummyExpenses
            )
        }
    }
}