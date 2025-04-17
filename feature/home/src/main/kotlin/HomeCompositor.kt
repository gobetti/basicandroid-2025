package com.example.feature.home

import androidx.compose.runtime.Composable
import com.example.feature.home.sources.HomeBarcodeSource
import com.example.feature.home.sources.HomePersistentCounterSource
import com.example.feature.home.sources.HomeSearchSource
import com.example.feature.home.sources.HomeTransientCounterSource
import com.eygraber.vice.ViceCompositor
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
internal class HomeCompositor(
    @Assisted private val onGoToListClick: () -> Unit,
    @Assisted private val onSearchResultClick: (String) -> Unit,
    private val barcodeSource: HomeBarcodeSource,
    private val persistentCounterSource: HomePersistentCounterSource,
    private val searchSource: HomeSearchSource,
    private val transientCounterSource: HomeTransientCounterSource
) : ViceCompositor<Intent, State> {
    @Composable
    override fun composite() = HomeViewState(
        isLoading = searchSource.isLoading(),
        barcode = barcodeSource.currentState(),
        searchResult = searchSource.currentState(),
        allTimeCounter = persistentCounterSource.currentState(),
        counter = transientCounterSource.currentState()
    )

    override suspend fun onIntent(intent: Intent) {
        when(intent) {
            is HomeAction.BarcodeChange -> barcodeSource.onBarcodeChange(intent.textFieldValue)
            HomeAction.OpenList -> {
                count()
                onGoToListClick()
            }
            is HomeAction.OpenProduct -> onSearchResultClick(intent.product.code)
            HomeAction.Reset -> reset()
            HomeAction.Search -> search()
        }
    }

    private suspend fun search() {
        searchSource()
    }

    private suspend fun count() {
        transientCounterSource.increment()

        persistentCounterSource.increment()
    }

    private suspend fun reset() {
        transientCounterSource.reset()

        persistentCounterSource.reset()
    }
}
