package com.example.myapplication.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SettingsDataStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserPreferencesDataStore

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @SettingsDataStore
    @Provides
    fun provideSettings(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.settings

    @UserPreferencesDataStore
    @Provides
    fun provideUserPreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.userPreferences
}

private val Context.settings: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val Context.userPreferences: DataStore<Preferences> by preferencesDataStore(name = "user")
