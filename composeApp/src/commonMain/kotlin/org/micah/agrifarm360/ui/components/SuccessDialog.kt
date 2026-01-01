package org.micah.agrifarm360.ui.components

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.check_24px
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessDialog() {
    var animate by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (animate) 1.2f else 0.8f,
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(Unit) {
        animate = true
    }

    BasicAlertDialog(
        onDismissRequest = {}
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 2.dp,
            modifier = Modifier.size(200.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.check_24px),
                    contentDescription = null,
                    tint = Color(0xFF25D366),
                    modifier = Modifier
                        .size(80.dp)
                        .scale(scale),
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Success!",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}