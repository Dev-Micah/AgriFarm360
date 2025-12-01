package org.micah.agrifarm360

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.micah.agrifarm360.data.local.Agrifarm360Database
import org.micah.agrifarm360.ui.navigation.AppNavHost
import org.micah.agrifarm360.ui.theme.AgriFarm360Theme

@Composable
@Preview
fun App(
    database: Agrifarm360Database
) {
    AgriFarm360Theme {
        AppNavHost()
    }
}