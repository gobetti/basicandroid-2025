package com.example.feature.product

internal sealed interface ProductIntent {
    data object Back : ProductIntent
}
