package com.example.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.base.ApplicationScope
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Qualifier

@Qualifier
@Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE
)
annotation class SettingsDataStore

@Qualifier
@Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE
)
annotation class UserPreferencesDataStore

interface DataStoreComponent {
    // convenience so we don't need to add context as a parameter to every method
    @get:Provides val context: Context

    @SettingsDataStore
    @Provides @ApplicationScope
    fun provideSettings(): DataStore<Preferences> = context.settings

    @UserPreferencesDataStore
    @Provides @ApplicationScope
    fun provideUserPreferences(): DataStore<Preferences> = context.userPreferences
}

private val Context.settings: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val Context.userPreferences: DataStore<Preferences> by preferencesDataStore(name = "user")
