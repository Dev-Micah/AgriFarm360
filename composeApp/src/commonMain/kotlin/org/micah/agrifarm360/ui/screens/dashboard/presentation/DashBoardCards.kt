package org.micah.agrifarm360.ui.screens.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.micah.agrifarm360.core.navigation.Destinations

@Composable
fun DashboardCards(){

}

@Composable
fun DashboardSummaryCards(
    totalRevenue: String,
    totalExpenses: String,
    activeWorkers: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        // Revenue Card
        SummaryCard(
            title = "Total Revenue",
            value = "KES $totalRevenue",
            valueColor = Color(0xFF0F9D58) // Green
        )

        Spacer(modifier = Modifier.size(12.dp))

        // Expenses Card
        SummaryCard(
            title = "Total Expenses",
            value = "KES $totalExpenses",
            valueColor = Color(0xFFE53935) // Red
        )

        Spacer(modifier = Modifier.size(12.dp))

        // Active Workers
        SummaryCard(
            title = "Active Workers",
            value = activeWorkers,
            valueColor = Color(0xFF0F9D58)
        )
    }
}

@Composable
fun SummaryCard(
    title: String,
    value: String,
    valueColor: Color,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = title,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = value,
            color = valueColor,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }
}

