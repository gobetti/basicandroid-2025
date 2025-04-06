package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.myapplication.feature.home.HomeView
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI.context = LocalContext.current
            App()
        }
    }
}

@Composable
fun App() {
    MyApplicationTheme {
        HomeView()
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}

object DI {
    lateinit var context: Context
    val database by lazy {
        Database(
            AndroidSqliteDriver(Database.Schema, context, "test.db")
        )
    }
}
