package com.example.myapplication.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import javax.inject.Inject

class HomeTransientCounterSource @Inject constructor() {
    private var counter by mutableIntStateOf(0)

    @Composable
    fun currentState() = counter

    fun increment() {
        counter += 1
    }

    fun reset() {
        counter = 0
    }
}
