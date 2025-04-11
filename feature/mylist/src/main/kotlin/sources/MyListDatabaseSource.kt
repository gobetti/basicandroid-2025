package com.example.feature.mylist.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.core.data.Database
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MyListDatabaseSource @Inject constructor(
    database: Database
) {
    private val flow =
        database
            .productQueries
            .findAll()
            .asFlow()
            .mapToList(Dispatchers.IO)

    @Composable
    fun currentState(): List<String> {
        val state by flow.collectAsStateWithLifecycle(initialValue = emptyList())
        return state
    }
}
