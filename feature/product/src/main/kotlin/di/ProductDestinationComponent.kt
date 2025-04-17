package com.example.feature.product.di

import com.example.core.di.ApplicationComponent
import com.example.core.di.ScreenScope
import com.example.feature.product.ProductDestination
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class ProductDestinationComponent(
    @Component val parent: ApplicationComponent
) {
    internal abstract val factory: (String, () -> Unit) -> ProductDestination
}
