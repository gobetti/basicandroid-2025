package com.example.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
    private val result = mutableStateOf<ProductData?>(null)
    private var isLoading = mutableStateOf(false)

    @Composable
    fun currentState() = rememberSaveable(result) { result }.value

    @Composable
    fun isLoading() = rememberSaveable { isLoading }.value

    suspend operator fun invoke() {
        isLoading.value = true

        try {
            val response = httpClient.get("https://world.openfoodfacts.org/api/v2/product/${barcodeSource.barcode}.json")
            val productData: ProductData = response.body()
            result.value = productData
            upsert(productData = productData)
        }
        catch (_: Throwable) {
            result.value = null
        }
        finally {
            isLoading.value = false
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
