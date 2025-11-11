package org.micah.agrifarm360.ui.screens.dashboard.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.add
import agrifarm360.composeapp.generated.resources.notifications
import agrifarm360.composeapp.generated.resources.profileicon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("AgriFarm360",
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ){
                        Image(
                            painter = painterResource(Res.drawable.profileicon),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },

                actions = {
                    IconButton(onClick = {}){
                        Icon(
                            painter = painterResource(Res.drawable.notifications),
                            contentDescription = "notifications"
                        )
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.padding(bottom = 80.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ){
                Icon(
                    painter = painterResource(Res.drawable.add),
                    contentDescription = "Add Task",
                    tint = Color.White
                )
            }
        }
    ) {

    }
}