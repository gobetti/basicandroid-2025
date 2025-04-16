package com.example.core.data.di

import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
annotation class JsonScope

@JsonScope
@Component
abstract class JsonComponent {
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
