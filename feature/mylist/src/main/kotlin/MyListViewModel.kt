package com.example.feature.mylist

import androidx.compose.runtime.Composable
import com.example.feature.mylist.sources.MyListDatabaseSource
import com.eygraber.vice.ViceCompositor
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
internal class MyListCompositor(
    @Assisted private val onBackClick: () -> Unit,
    @Assisted private val onItemClick: (String) -> Unit,
    val databaseSource: MyListDatabaseSource
) : ViceCompositor<Intent, State> {
    @Composable
    override fun composite() = State(
        items = databaseSource.currentState()
    )

    override suspend fun onIntent(intent: Intent) {
        when(intent) {
            MyListIntent.Back -> onBackClick()
            is MyListIntent.OpenItem -> onItemClick(intent.item)
        }
    }
}
