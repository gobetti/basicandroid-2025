package com.example.myapplication.feature.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeViewState(
    val barcode: String,
    val searchResult: String?,
    val entries: List<String>,
    val allTimeCounter: Int,
    val counter: Int
)
