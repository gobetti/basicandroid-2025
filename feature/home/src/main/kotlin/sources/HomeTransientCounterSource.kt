package com.example.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.feature.home.di.HomeViewScope
import me.tatarka.inject.annotations.Inject

@HomeViewScope
@Inject
class HomeTransientCounterSource {
    private var counter = mutableIntStateOf(0)

    @Composable
    fun currentState() = rememberSaveable { counter }.intValue

    fun increment() {
        counter.intValue += 1
    }

    fun reset() {
        counter.intValue = 0
    }
}
