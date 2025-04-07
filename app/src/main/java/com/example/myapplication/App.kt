package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.feature.home.Home
import com.example.myapplication.feature.home.HomeRoute
import com.example.myapplication.feature.mylist.MyList
import com.example.myapplication.feature.mylist.MyListRoute
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun App() {
    MyApplicationTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Home
        ) {
            composable<Home> {
                HomeRoute(
                    onGoToListClick = { navController.navigate(MyList) }
                )
            }

            composable<MyList> {
                MyListRoute(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
