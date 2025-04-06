package com.example.myapplication.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import javax.inject.Inject

class HomeBarcodeSource @Inject constructor() {
    private var barcode by mutableStateOf("0030000436073")

    val value get() = barcode

    @Composable
    fun currentState() = barcode

    fun onBarcodeChange(text: String) {
        barcode = text.take(13)
    }
}
