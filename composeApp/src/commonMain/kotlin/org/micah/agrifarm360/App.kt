package org.micah.agrifarm360

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.micah.agrifarm360.ui.navigation.AppNavHost
import org.micah.agrifarm360.features.tasks.data.local.TaskDatabase
import org.micah.agrifarm360.ui.theme.AgriFarm360Theme

@Composable
@Preview
fun App(
    database: TaskDatabase
) {
    AgriFarm360Theme {
        AppNavHost()
    }
}