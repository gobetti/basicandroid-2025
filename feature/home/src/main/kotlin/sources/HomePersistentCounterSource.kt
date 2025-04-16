package com.example.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.data.di.SettingsDataStore
import com.example.feature.home.di.HomeViewScope
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@HomeViewScope
@Inject
class HomePersistentCounterSource(
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
