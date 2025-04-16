package com.example.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.core.data.Database
import com.example.core.data.ProductData
import com.example.core.di.ScreenScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@ScreenScope
@Inject
class HomeSearchSource(
    private val barcodeSource: HomeBarcodeSource,
    private val database: Database,
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

        try {
            val response = httpClient.get("https://world.openfoodfacts.org/api/v2/product/${barcodeSource.value}.json")
            val productData: ProductData = response.body()
            result = productData
            upsert(productData = productData)
        }
        catch (_: Throwable) {
            result = null
        }
        finally {
            isLoading = false
        }
    }

    private suspend fun upsert(productData: ProductData) = withContext(Dispatchers.IO) {
        database.transaction {
            with(productData) {
                database.productQueries.upsert(
                    barcode = code,
                    productJson = productJson
                )
            }
        }
    }
}
