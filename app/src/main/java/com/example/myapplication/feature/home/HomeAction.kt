package com.example.myapplication.feature.home

internal sealed interface HomeAction {
    data class BarcodeChange(val barcode: String) : HomeAction
    data object Clear : HomeAction
    data object Insert : HomeAction
    data object Search : HomeAction
}
