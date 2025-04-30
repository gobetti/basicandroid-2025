package com.example.feature.product.di

import com.example.core.di.ApplicationComponent
import com.example.feature.product.ProductCompositor
import com.example.core.di.ScreenScope
import com.example.feature.product.ProductDestination
import me.tatarka.inject.annotations.AssistedFactory
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class ProductDestinationComponent(
    @Component val parent: ApplicationComponent
) {
    abstract val compositorFactory: ProductCompositorFactory

    fun build(
        barcode: String,
        onBackClick: () -> Unit
    ) = ProductDestination(
        compositor = compositorFactory(
            barcode = barcode,
            onBackClick = onBackClick
        )
    )
}

@AssistedFactory
internal interface ProductCompositorFactory {
    operator fun invoke(
        barcode: String,
        onBackClick: () -> Unit
    ): ProductCompositor
}
