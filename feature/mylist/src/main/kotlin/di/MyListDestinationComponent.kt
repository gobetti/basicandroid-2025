package com.example.feature.mylist.di

import com.example.core.di.ApplicationComponent
import com.example.feature.mylist.MyListDestination
import me.tatarka.inject.annotations.Component

@Component
internal abstract class MyListDestinationComponent(
    @Component val parent: ApplicationComponent
) {
    internal abstract val factory: (() -> Unit, (String) -> Unit) -> MyListDestination
}
