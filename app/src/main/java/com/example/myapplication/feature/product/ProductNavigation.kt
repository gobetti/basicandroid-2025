package com.example.myapplication.feature.product

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.data.ProductData
import kotlinx.serialization.Serializable

@Serializable
data class Product(val productData: ProductData)

fun NavController.navigateToProduct(productData: ProductData) =
    navigate(route = Product(productData = productData))

fun NavGraphBuilder.productScreen(
    onBackClick: () -> Unit
) {
    composable<Product> {
        ProductRoute(
            onBackClick = onBackClick
        )
    }
}
