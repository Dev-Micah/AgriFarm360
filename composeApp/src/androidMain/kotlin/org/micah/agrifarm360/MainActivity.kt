package org.micah.agrifarm360

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.micah.agrifarm360.core.navigation.AppNavHost
import org.micah.agrifarm360.db.getDatabaseBuilder
import org.micah.agrifarm360.features.tasks.data.local.getTaskDatabase
import org.micah.agrifarm360.ui.theme.AgriFarm360Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                database = getTaskDatabase(getDatabaseBuilder(this@MainActivity))
            )

        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val database = getTaskDatabase(getDatabaseBuilder(LocalContext.current))
    App(database)

}