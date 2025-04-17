package com.example.myapplication.feature.mylist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object MyList

fun NavController.navigateToMyList() =
    navigate(route = MyList)

fun NavGraphBuilder.myListScreen(
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    composable<MyList> {
        MyListRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}
