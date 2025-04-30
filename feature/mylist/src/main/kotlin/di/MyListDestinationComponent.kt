package com.example.feature.mylist.di

import com.example.core.di.ApplicationComponent
import com.example.feature.mylist.MyListCompositor
import com.example.core.di.ScreenScope
import com.example.feature.mylist.MyListDestination
import me.tatarka.inject.annotations.AssistedFactory
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class MyListDestinationComponent(
    @Component val parent: ApplicationComponent
) {
    abstract val compositorFactory: MyListCompositorFactory

    fun build(
        onBackClick: () -> Unit,
        onItemClick: (String) -> Unit
    ) = MyListDestination(
        compositor = compositorFactory(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    )
}

@AssistedFactory
internal interface MyListCompositorFactory {
    operator fun invoke(
        onBackClick: () -> Unit,
        onItemClick: (String) -> Unit
    ): MyListCompositor
}
