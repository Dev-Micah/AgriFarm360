package org.micah.agrifarm360.ui.screens.expenses.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Expenses(
    val name: String,
    val amount: Double,
    val category: String,
    val date: String,
    val description: String
)

val dummyExpenses = listOf(
    Expenses(
        "Allowances", 35000.8,
        "Salary", "12 Oct 2025",
        "Paid workers Allowances"
    )
)

@Composable
fun ExpensesListScreen(expenses: List<Expenses>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(expenses) { expense ->
            ExpenseItem(
                expense = expense,
            )
        }
    }
}

@Composable
fun ExpenseItem(expense: Expenses) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE6F4EA)),
                contentAlignment = Alignment.Center
            ) {
                val initials = expense.name
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

            Column {
                Text(
                    text = expense.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF1C1C1C)
                )
                Text(
                    text = expense.description,
                    color = Color(0xFF6B7280),
                    fontSize = 14.sp
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "KES ${(expense.amount)}",
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD32F2F),
                fontSize = 16.sp
            )
            Text(
                text = expense.date,
                color = Color(0xFF6B7280),
                fontSize = 14.sp
            )
        }
    }
}
