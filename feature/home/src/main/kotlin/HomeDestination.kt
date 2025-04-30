package com.example.feature.home

import androidx.compose.runtime.Composable
import com.eygraber.vice.ViceEffects
import com.eygraber.vice.nav.ViceDestination

internal typealias Intent = HomeAction
internal typealias Compositor = HomeCompositor
internal typealias State = HomeViewState
internal typealias View = @Composable (State, (Intent) -> Unit) -> Unit

internal class HomeDestination(
    override val compositor: Compositor
) : ViceDestination<Intent, Compositor, ViceEffects, State>() {
    override val view: View = { state, onIntent ->
        HomeView(onIntent, state)
    }
    override val effects = ViceEffects.None
}
