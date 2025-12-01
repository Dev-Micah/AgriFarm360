package org.micah.agrifarm360

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.micah.agrifarm360.data.local.getAgrifarm360Database
import org.micah.agrifarm360.db.getDatabaseBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
               database = getAgrifarm360Database(getDatabaseBuilder(this@MainActivity))
            )

        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val database = getAgrifarm360Database(getDatabaseBuilder(LocalContext.current))
    App(database)

}