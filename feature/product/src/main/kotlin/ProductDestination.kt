package com.example.feature.product

import androidx.compose.runtime.Composable
import com.eygraber.vice.ViceEffects
import com.eygraber.vice.nav.ViceDestination
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

internal typealias Intent = ProductIntent
internal typealias Compositor = ProductCompositor
internal typealias State = ProductViewState
internal typealias View = @Composable (State, (Intent) -> Unit) -> Unit

@Inject
internal class ProductDestination(
    @Assisted barcode: String,
    @Assisted onBackClick: () -> Unit,
    compositorFactory: (String, () -> Unit) -> Compositor
) : ViceDestination<Intent, Compositor, ViceEffects, State>() {
    override val view: View = { state, onIntent ->
        ProductView(onIntent, state)
    }
    override val compositor = compositorFactory(barcode, onBackClick)
    override val effects = ViceEffects.None
}
