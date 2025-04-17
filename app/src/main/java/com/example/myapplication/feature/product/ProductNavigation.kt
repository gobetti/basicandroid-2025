package com.example.myapplication.feature.product

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class Product(val barcode: String)

fun NavController.navigateToProduct(barcode: String) =
    navigate(route = Product(barcode = barcode))

fun NavGraphBuilder.productScreen(
    onBackClick: () -> Unit
) {
    composable<Product> {
        ProductRoute(
            onBackClick = onBackClick
        )
    }
}
