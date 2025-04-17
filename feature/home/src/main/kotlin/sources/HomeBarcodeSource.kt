package com.example.feature.home.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.coerceIn
import androidx.compose.ui.text.input.TextFieldValue
import com.example.core.di.ScreenScope
import me.tatarka.inject.annotations.Inject

@ScreenScope
@Inject
class HomeBarcodeSource {
    private var textFieldValue = mutableStateOf(TextFieldValue("0030000436073"))

    val barcode get() = textFieldValue.value.text

    // FIXME: although the value is saved on rotation, the IME breaks completely
    @Composable
    fun currentState() =
        rememberSaveable(stateSaver = TextFieldValue.Saver) { textFieldValue }.value

    fun onBarcodeChange(value: TextFieldValue) {
        val maxLength = 13
        textFieldValue.value = value.copy(
            text = value.text.take(maxLength),
            selection = value.selection.coerceIn(0, maxLength),
            composition = value.composition?.coerceIn(0, maxLength)
        )
    }
}
