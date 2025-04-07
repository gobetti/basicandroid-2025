package com.example.myapplication.feature.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.home.sources.HomeBarcodeSource
import com.example.myapplication.feature.home.sources.HomePersistentCounterSource
import com.example.myapplication.feature.home.sources.HomeSearchSource
import com.example.myapplication.feature.home.sources.HomeTransientCounterSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
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
