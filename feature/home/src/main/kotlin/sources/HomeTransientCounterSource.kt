package com.example.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.core.di.ScreenScope
import me.tatarka.inject.annotations.Inject

@ScreenScope
@Inject
class HomeTransientCounterSource {
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
