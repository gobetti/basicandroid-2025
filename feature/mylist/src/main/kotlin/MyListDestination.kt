package com.example.feature.mylist

import androidx.compose.runtime.Composable
import com.eygraber.vice.ViceEffects
import com.eygraber.vice.nav.ViceDestination
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

internal typealias Intent = MyListIntent
internal typealias Compositor = MyListCompositor
internal typealias State = MyListViewState
internal typealias View = @Composable (State, (Intent) -> Unit) -> Unit

@Inject
internal class MyListDestination(
    @Assisted onBackClick: () -> Unit,
    @Assisted onItemClick: (String) -> Unit,
    compositorFactory: (() -> Unit, (String) -> Unit) -> Compositor
) : ViceDestination<Intent, Compositor, ViceEffects, State>() {
    override val view: View = { state, onIntent ->
        MyListView(onIntent, state)
    }
    override val compositor = compositorFactory(onBackClick, onItemClick)
    override val effects = ViceEffects.None
}

