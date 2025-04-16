package com.example.feature.mylist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ApplicationComponent
import com.example.core.di.ScreenScope
import com.example.feature.mylist.MyListViewModel
import me.tatarka.inject.annotations.Component

@ScreenScope
@Component
internal abstract class MyListViewModelFactory(
    @Component val parent: ApplicationComponent
) : ViewModelProvider.Factory {
    abstract val myListViewModelFactory: () -> MyListViewModel

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return myListViewModelFactory() as T
    }
}
