package com.example.myapplication.feature.home

import androidx.compose.runtime.Immutable
import com.example.myapplication.data.ProductData

@Immutable
data class HomeViewState(
    val isLoading: Boolean,
    val barcode: String,
    val searchResult: ProductData?,
    val allTimeCounter: Int,
    val counter: Int
)
