package com.example.myapplication.feature.home

import com.example.myapplication.data.ProductData

internal sealed interface HomeAction {
    data class BarcodeChange(val barcode: String) : HomeAction
    data object OpenList : HomeAction
    data class OpenProduct(val product: ProductData) : HomeAction
    data object Reset : HomeAction
    data object Search : HomeAction
}
