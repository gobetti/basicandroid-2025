package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.feature.home.Home
import com.example.myapplication.feature.home.homeScreen
import com.example.myapplication.feature.mylist.myListScreen
import com.example.myapplication.feature.mylist.navigateToMyList
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun App() {
    MyApplicationTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Home
        ) {
            homeScreen(
                onGoToListClick = { navController.navigateToMyList() }
            )

            myListScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
