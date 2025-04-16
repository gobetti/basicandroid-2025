package com.example.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
annotation class NetworkScope

@NetworkScope
@Component
abstract class NetworkComponent {
    @Provides
    fun provideHttpClient(json: Json): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
    }
}
