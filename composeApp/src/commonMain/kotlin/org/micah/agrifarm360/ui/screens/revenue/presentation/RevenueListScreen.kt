package org.micah.agrifarm360.ui.screens.revenue.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.micah.agrifarm360.domain.models.Revenue
import org.micah.agrifarm360.utils.timestampToDateTime

@Composable
fun RevenueListScreen(
    revenues: List<Revenue>,
    modifier: Modifier = Modifier
                      ) {
    Column {
        revenues.forEach { revenue ->
            RevenueItem(revenue = revenue)
        }
    }
}

@Composable
fun RevenueItem(revenue: Revenue) {
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
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                val initials = revenue.source
                    .split(" ")
                    .filter { it.isNotBlank() }
                    .take(2)
                    .joinToString("") { it.first().uppercase() }
                    .ifEmpty { "?" }

                Text(
                    text = initials,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = revenue.source,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "+ KES ${formatAmount(revenue.amount)}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
            Text(
                text = revenue.date.timestampToDateTime(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
        }
    }
}

private fun formatAmount(amount: Double): String {
    val longValue = amount.toLong()
    return longValue.toString().reversed().chunked(3).joinToString(",").reversed()
}
