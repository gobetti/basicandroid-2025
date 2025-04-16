package com.example.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.feature.home.di.HomeViewScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Inject
import me.tatarka.inject.annotations.Provides

@HomeViewScope
@Component
abstract class HomeBarcodeSourceModule {
    @HomeViewScope
    @Provides
    fun provideBarcodeSource(): HomeBarcodeSource = HomeBarcodeSource()
}

@HomeViewScope
@Inject
class HomeBarcodeSource {
    private var barcode by mutableStateOf("0030000436073")

    val value get() = barcode

    @Composable
    fun currentState() = barcode

    fun onBarcodeChange(text: String) {
        barcode = text.take(13)
    }
}
