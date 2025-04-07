package com.example.myapplication.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.data.ProductData
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeScreen(
    onGoToListClick: () -> Unit,
    onSearchResultClick: (ProductData) -> Unit
) {
    composable<Home> {
        HomeRoute(
            onGoToListClick = onGoToListClick,
            onSearchResultClick = onSearchResultClick
        )
    }
}
