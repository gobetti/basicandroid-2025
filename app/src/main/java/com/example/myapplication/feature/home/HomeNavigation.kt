package com.example.myapplication.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeScreen(
    onGoToListClick: () -> Unit
) {
    composable<Home> {
        HomeRoute(
            onGoToListClick = onGoToListClick
        )
    }
}
