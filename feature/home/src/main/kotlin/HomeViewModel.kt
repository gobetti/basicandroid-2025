package com.example.feature.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.di.ScreenScope
import com.example.feature.home.sources.HomeBarcodeSource
import com.example.feature.home.sources.HomePersistentCounterSource
import com.example.feature.home.sources.HomeSearchSource
import com.example.feature.home.sources.HomeTransientCounterSource
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@ScreenScope
@Inject
internal class HomeViewModel(
    val barcodeSource: HomeBarcodeSource,
    val persistentCounterSource: HomePersistentCounterSource,
    val searchSource: HomeSearchSource,
    val transientCounterSource: HomeTransientCounterSource
) : ViewModel() {
    @Composable
    fun viewState() = HomeViewState(
        isLoading = searchSource.isLoading(),
        barcode = barcodeSource.currentState(),
        searchResult = searchSource.currentState(),
        allTimeCounter = persistentCounterSource.currentState(),
        counter = transientCounterSource.currentState()
    )

    fun search() {
        viewModelScope.launch {
            searchSource()
        }
    }

    fun count() {
        transientCounterSource.increment()

        viewModelScope.launch {
            persistentCounterSource.increment()
        }
    }

    fun reset() {
        transientCounterSource.reset()

        viewModelScope.launch {
            persistentCounterSource.reset()
        }
    }
}
