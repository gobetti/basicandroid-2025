package com.example.feature.product

import androidx.compose.runtime.Composable
import com.eygraber.vice.ViceEffects
import com.eygraber.vice.nav.ViceDestination

internal typealias Intent = ProductIntent
internal typealias Compositor = ProductCompositor
internal typealias State = ProductViewState
internal typealias View = @Composable (State, (Intent) -> Unit) -> Unit

internal class ProductDestination(
    override val compositor: Compositor
) : ViceDestination<Intent, Compositor, ViceEffects, State>() {
    override val view: View = { state, onIntent ->
        ProductView(onIntent, state)
    }
    override val effects = ViceEffects.None
}
