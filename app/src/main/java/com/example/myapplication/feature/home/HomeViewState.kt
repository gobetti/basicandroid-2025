package com.example.myapplication.feature.home

import androidx.compose.runtime.Immutable
import com.example.myapplication.data.Product

@Immutable
data class HomeViewState(
    val isLoading: Boolean,
    val barcode: String,
    val searchResult: Product?,
    val entries: List<String>,
    val allTimeCounter: Int,
    val counter: Int
)
