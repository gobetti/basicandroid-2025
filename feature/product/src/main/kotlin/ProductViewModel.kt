package com.example.feature.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import com.example.core.data.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.jsonPrimitive
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
internal class ProductViewModel(
    database: Database,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val barcode = savedStateHandle.toRoute<Product>().barcode

    private val productFlow =
        database
            .productQueries
            .find(whereBarcode = barcode) { barcode, productJson ->
                DisplayableProduct(
                    barcode = barcode,
                    name = productJson["product_name"]?.jsonPrimitive?.content ?: "Name unavailable"
                )
            }
            .asFlow()
            .mapToOne(Dispatchers.IO)

    val product =
        productFlow
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = DisplayableProduct(
                    barcode = barcode,
                    name = ""
                )
            )

    data class DisplayableProduct(
        val barcode: String,
        val name: String
    )
}
