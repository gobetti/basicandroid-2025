package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.core.di.applicationComponent
import com.example.core.ui.MyApplicationTheme
import com.example.feature.home.Home
import com.example.feature.home.homeScreen
import com.example.feature.mylist.myListScreen
import com.example.feature.mylist.navigateToMyList
import com.example.feature.product.navigateToProduct
import com.example.feature.product.productScreen

@Composable
fun App() {
    val applicationComponent = LocalContext.current.applicationComponent

    MyApplicationTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Home
        ) {
            homeScreen(
                applicationComponent = applicationComponent,
                onGoToListClick = navController::navigateToMyList,
                onSearchResultClick = navController::navigateToProduct
            )

            myListScreen(
                onBackClick = navController::popBackStack,
                onItemClick = navController::navigateToProduct
            )

            productScreen(
                onBackClick = navController::popBackStack
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
