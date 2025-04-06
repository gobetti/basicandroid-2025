package com.example.myapplication.feature.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.myapplication.Database
import com.example.myapplication.MyTable
import com.example.myapplication.data.Product
import com.example.myapplication.di.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val database: Database,
    private val httpClient: HttpClient,
    @SettingsDataStore private val settings: DataStore<Preferences>
) : ViewModel() {
    private val barcode = MutableStateFlow("0030000436073")
    private val searchResult = MutableStateFlow<Product?>(null)
    private val counter = MutableStateFlow(0)

    private val entriesFlow =
        database
            .myTableQueries
            .findAll()
            .asFlow()
            .mapToList(Dispatchers.IO)

    private val allTimeCounterFlow =
        settings.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }

    val viewState = combine(
        barcode,
        searchResult,
        entriesFlow,
        allTimeCounterFlow,
        counter
    ) { barcode, searchResult, entries, allTimeCounter, counter ->
        HomeViewState(
            barcode = barcode,
            searchResult = searchResult,
            entries = entries,
            allTimeCounter = allTimeCounter,
            counter = counter
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = HomeViewState(
            barcode = barcode.value,
            searchResult = searchResult.value,
            entries = emptyList(),
            allTimeCounter = 0,
            counter = counter.value
        )
    )

    fun onBarcodeChange(text: String) {
        barcode.value = text.take(13)
    }

    fun search() {
        viewModelScope.launch {
            val response = httpClient.get("https://world.openfoodfacts.org/api/v2/product/$barcode.json")
            searchResult.value = response.body()
        }
    }

    fun insert() {
        counter.value += 1

        viewModelScope.launch {
            settings.edit { preferences ->
                val currentCounterValue = preferences[COUNTER_KEY] ?: 0
                preferences[COUNTER_KEY] = currentCounterValue + 1
            }
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.transaction {
                    database.myTableQueries.insert(
                        MyTable(UUID.randomUUID().toString())
                    )
                }
            }
        }
    }

    fun clear() {
        counter.value = 0

        viewModelScope.launch {
            settings.edit { preferences ->
                preferences.remove(COUNTER_KEY)
            }
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.transaction {
                    database.myTableQueries.deleteAll()
                }
            }
        }
    }

    private companion object {
        val COUNTER_KEY = intPreferencesKey("counter")
    }
}
