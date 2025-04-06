package com.example.myapplication.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.example.myapplication.di.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
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
    var barcode by mutableStateOf("0030000436073")
        private set

    fun onBarcodeChange(text: String) {
        barcode = text.take(13)
    }

    var searchResult by mutableStateOf<String?>(null)
        private set

    fun search() {
        viewModelScope.launch {
            val response = httpClient.get("https://world.openfoodfacts.org/api/v2/product/$barcode.json")
            searchResult = response.bodyAsText()
        }
    }

    private val myTableFlow =
        database
            .myTableQueries
            .findAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
    val myTableEntries = myTableFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = emptyList()
    )

    private val allTimeCounterFlow =
        settings.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }
    val allTimeCounter = allTimeCounterFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = 0
    )

    var counter by mutableIntStateOf(0)
        private set

    fun insert() {
        counter += 1

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
        counter = 0

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
