package com.example.myapplication.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
object HomeBarcodeSourceModule {
    @Provides
    @ViewModelScoped
    fun provideBarcodeSource(): HomeBarcodeSource = HomeBarcodeSource()
}

class HomeBarcodeSource @Inject constructor() {
    private var barcode by mutableStateOf("0030000436073")

    val value get() = barcode

    @Composable
    fun currentState() = barcode

    fun onBarcodeChange(text: String) {
        barcode = text.take(13)
    }
}
