package com.example.feature.product

import androidx.compose.runtime.Immutable

@Immutable
internal data class ProductViewState(
    val barcode: String,
    val name: String
)
