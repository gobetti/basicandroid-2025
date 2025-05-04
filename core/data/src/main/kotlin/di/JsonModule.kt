package com.example.core.data.di

import com.example.core.base.ApplicationScope
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Provides

interface JsonComponent {
    @Provides @ApplicationScope
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
