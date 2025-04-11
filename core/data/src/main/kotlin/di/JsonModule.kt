package com.example.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object JsonModule {
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
