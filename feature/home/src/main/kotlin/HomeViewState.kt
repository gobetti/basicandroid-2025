package com.example.feature.home

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import com.example.core.data.ProductData

@Immutable
internal data class HomeViewState(
    val isLoading: Boolean,
    val barcode: TextFieldValue,
    val searchResult: ProductData?,
    val allTimeCounter: Int,
    val counter: Int
)
