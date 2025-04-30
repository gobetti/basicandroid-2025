package com.example.feature.home.di

import com.example.core.di.ApplicationComponent
import com.example.feature.home.HomeCompositor
import com.example.core.di.ScreenScope
import com.example.feature.home.HomeDestination
import me.tatarka.inject.annotations.AssistedFactory
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class HomeDestinationComponent(
    @Component val parent: ApplicationComponent
) {
    abstract val compositorFactory: HomeCompositorFactory

    fun build(
        onGoToListClick: () -> Unit,
        onSearchResultClick: (String) -> Unit
    ) = HomeDestination(
        compositor = compositorFactory(
            onGoToListClick = onGoToListClick,
            onSearchResultClick = onSearchResultClick
        )
    )
}

@AssistedFactory
internal interface HomeCompositorFactory {
    operator fun invoke(
        onGoToListClick: () -> Unit,
        onSearchResultClick: (String) -> Unit
    ): HomeCompositor
}
