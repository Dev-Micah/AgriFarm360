package org.micah.agrifarm360.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorTheme  = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    //searchColor = TextFieldColor
)

@Composable
fun AgriFarm360Theme(content: @Composable () -> Unit){

//    val theme = if (isSystemInDarkTheme()){
//        DarkColorTheme
//    } else {
//        LightColorTheme
//    }
    MaterialTheme(
        colorScheme = LightColorTheme,
        content = content
    )

}