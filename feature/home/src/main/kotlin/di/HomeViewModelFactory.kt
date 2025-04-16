package com.example.feature.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ApplicationComponent
import com.example.core.di.ScreenScope
import com.example.feature.home.HomeViewModel
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class HomeViewModelFactory(
    @Component val parent: ApplicationComponent
) : ViewModelProvider.Factory {
    abstract val homeViewModelFactory: () -> HomeViewModel

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return homeViewModelFactory() as T
    }
}
