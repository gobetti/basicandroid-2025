package com.example.myapplication.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.data.ProductData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class HomeSearchSource @Inject constructor(
    private val barcodeSource: HomeBarcodeSource,
    private val httpClient: HttpClient
) {
    private var result by mutableStateOf<ProductData?>(null)
    private var isLoading by mutableStateOf(false)

    @Composable
    fun currentState() = result

    @Composable
    fun isLoading() = isLoading

    suspend operator fun invoke() {
        isLoading = true

        val response = httpClient.get("https://world.openfoodfacts.org/api/v2/product/${barcodeSource.value}.json")
        result = response.body()

        isLoading = false
    }
}
