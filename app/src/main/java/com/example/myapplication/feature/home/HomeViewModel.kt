package com.example.myapplication.feature.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.home.sources.HomeBarcodeSource
import com.example.myapplication.feature.home.sources.HomeDatabaseSource
import com.example.myapplication.feature.home.sources.HomePersistentCounterSource
import com.example.myapplication.feature.home.sources.HomeSearchSource
import com.example.myapplication.feature.home.sources.HomeTransientCounterSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val barcodeSource: HomeBarcodeSource,
    val databaseSource: HomeDatabaseSource,
    val persistentCounterSource: HomePersistentCounterSource,
    val searchSource: HomeSearchSource,
    val transientCounterSource: HomeTransientCounterSource
) : ViewModel() {
    @Composable
    fun viewState() = HomeViewState(
        isLoading = searchSource.isLoading(),
        barcode = barcodeSource.currentState(),
        searchResult = searchSource.currentState(),
        entries = databaseSource.currentState(),
        allTimeCounter = persistentCounterSource.currentState(),
        counter = transientCounterSource.currentState()
    )

    fun search() {
        viewModelScope.launch {
            searchSource()
        }
    }

    fun insert() {
        transientCounterSource.increment()

        viewModelScope.launch {
            persistentCounterSource.increment()
        }

        viewModelScope.launch {
            databaseSource.insert()
        }
    }

    fun clear() {
        transientCounterSource.reset()

        viewModelScope.launch {
            persistentCounterSource.reset()
        }

        viewModelScope.launch {
            databaseSource.deleteAll()
        }
    }
}
