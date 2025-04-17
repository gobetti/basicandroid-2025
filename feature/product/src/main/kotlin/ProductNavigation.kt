package com.example.feature.product

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.core.di.ApplicationComponent
import com.example.feature.product.di.ProductDestinationComponent
import com.example.feature.product.di.create
import com.eygraber.vice.nav.viceComposable
import kotlinx.serialization.Serializable

@Serializable
internal data class Product(val barcode: String)

fun NavController.navigateToProduct(barcode: String) =
    navigate(route = Product(barcode = barcode))

fun NavGraphBuilder.productScreen(
    applicationComponent: ApplicationComponent,
    onBackClick: () -> Unit
) {
    viceComposable<Product> { backStackEntry ->
        ProductDestinationComponent::class.create(applicationComponent).factory(
            backStackEntry.route.barcode,
            onBackClick
        )
    }
}
