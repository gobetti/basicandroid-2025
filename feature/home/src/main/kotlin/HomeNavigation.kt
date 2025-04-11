package com.example.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeScreen(
    onGoToListClick: () -> Unit,
    onSearchResultClick: (String) -> Unit
) {
    composable<Home> {
        HomeRoute(
            onGoToListClick = onGoToListClick,
            onSearchResultClick = onSearchResultClick
        )
    }
}
