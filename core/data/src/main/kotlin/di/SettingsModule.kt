package com.example.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Qualifier
import me.tatarka.inject.annotations.Scope

@Scope
annotation class DataStoreScope

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

@DataStoreScope
@Component
abstract class DataStoreComponent(
    private val context: Context
) {
    @SettingsDataStore
    @Provides
    fun provideSettings(): DataStore<Preferences> = context.settings

    @UserPreferencesDataStore
    @Provides
    fun provideUserPreferences(): DataStore<Preferences> = context.userPreferences
}

private val Context.settings: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val Context.userPreferences: DataStore<Preferences> by preferencesDataStore(name = "user")
