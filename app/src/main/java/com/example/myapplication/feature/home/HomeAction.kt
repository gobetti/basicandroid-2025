package com.example.myapplication.feature.home

internal sealed interface HomeAction {
    data class BarcodeChange(val barcode: String) : HomeAction
    data object OpenList : HomeAction
    data object Reset : HomeAction
    data object Search : HomeAction
}
