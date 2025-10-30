package org.micah.agrifarm360.ui.screens.splash.presentation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.agrifarm
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(){
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ){
            Image(
                painter = painterResource(Res.drawable.agrifarm),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
        }

}
//0xFF25D366