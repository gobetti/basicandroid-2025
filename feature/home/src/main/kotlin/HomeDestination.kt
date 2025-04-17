package com.example.feature.home

import androidx.compose.runtime.Composable
import com.eygraber.vice.ViceEffects
import com.eygraber.vice.nav.ViceDestination
import kotlinx.serialization.Serializable
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

internal typealias Intent = HomeAction
internal typealias Compositor = HomeCompositor
internal typealias State = HomeViewState
internal typealias View = @Composable (State, (Intent) -> Unit) -> Unit

@Inject
internal class HomeDestination(
    @Assisted onGoToListClick: () -> Unit,
    @Assisted onSearchResultClick: (String) -> Unit,
    compositorFactory: (() -> Unit, (String) -> Unit) -> Compositor
) : ViceDestination<Intent, Compositor, ViceEffects, State>() {
    override val view: View = { state, onIntent ->
        HomeView(onIntent, state)
    }
    override val compositor = compositorFactory(onGoToListClick, onSearchResultClick)
    override val effects = ViceEffects.None
}
