package com.example.feature.product

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import com.example.core.data.Database
import com.eygraber.vice.ViceCompositor
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.jsonPrimitive
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
internal class ProductCompositor(
    database: Database,
    @Assisted private val barcode: String,
    @Assisted private val onBackClick: () -> Unit
) : ViceCompositor<Intent, State> {
    private val productFlow =
        database
            .productQueries
            .find(whereBarcode = barcode) { barcode, productJson ->
                ProductViewState(
                    barcode = barcode,
                    name = productJson["product_name"]?.jsonPrimitive?.content ?: "Name unavailable"
                )
            }
            .asFlow()
            .mapToOne(Dispatchers.IO)

    @Composable
    override fun composite(): State {
        val product = productFlow.collectAsStateWithLifecycle(
            initialValue = State(
                barcode = barcode,
                name = ""
            )
        )
        return product.value
    }

    override suspend fun onIntent(intent: Intent) {
        when(intent) {
            ProductIntent.Back -> onBackClick()
        }
    }
}
