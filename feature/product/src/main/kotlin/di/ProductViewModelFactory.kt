package com.example.feature.product.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.core.di.ApplicationComponent
import com.example.core.di.ScreenScope
import com.example.feature.product.ProductViewModel
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class ProductViewModelFactory(
    @Component val parent: ApplicationComponent
) : ViewModelProvider.Factory {
    abstract val productViewModelFactory: (SavedStateHandle) -> ProductViewModel

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        @Suppress("UNCHECKED_CAST")
        return productViewModelFactory(extras.createSavedStateHandle()) as T
    }
}
