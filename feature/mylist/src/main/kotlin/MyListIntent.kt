package com.example.feature.mylist

internal sealed interface MyListIntent {
    data object Back : MyListIntent
    data class OpenItem(val item: String) : MyListIntent
}
