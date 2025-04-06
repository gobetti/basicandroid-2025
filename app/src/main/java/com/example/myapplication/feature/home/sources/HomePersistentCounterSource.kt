package com.example.myapplication.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.di.SettingsDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomePersistentCounterSource @Inject constructor(
    @SettingsDataStore private val settings: DataStore<Preferences>
) {
    private val flow =
        settings.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }

    @Composable
    fun currentState(): Int {
        val state by flow.collectAsStateWithLifecycle(initialValue = 0)
        return state
    }

    suspend fun increment() {
        settings.edit { preferences ->
            val currentCounterValue = preferences[COUNTER_KEY] ?: 0
            preferences[COUNTER_KEY] = currentCounterValue + 1
        }
    }

    suspend fun reset() {
        settings.edit { preferences ->
            preferences.remove(COUNTER_KEY)
        }
    }

    private companion object {
        val COUNTER_KEY = intPreferencesKey("counter")
    }
}
