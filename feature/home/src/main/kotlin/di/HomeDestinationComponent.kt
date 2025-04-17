package com.example.feature.home.di

import com.example.core.di.ApplicationComponent
import com.example.core.di.ScreenScope
import com.example.feature.home.HomeDestination
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class HomeDestinationComponent(
    @Component val parent: ApplicationComponent
) {
    internal abstract val factory: (() -> Unit, (String) -> Unit) -> HomeDestination
}
