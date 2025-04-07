package com.example.myapplication.feature.mylist.sources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.myapplication.Database
import com.example.myapplication.MyTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class MyListDatabaseSource @Inject constructor(
    private val database: Database
) {
    private val flow =
        database
            .myTableQueries
            .findAll()
            .asFlow()
            .mapToList(Dispatchers.IO)

    @Composable
    fun currentState(): List<String> {
        val state by flow.collectAsStateWithLifecycle(initialValue = emptyList())
        return state
    }

    suspend fun insert() = withContext(Dispatchers.IO) {
        database.transaction {
            database.myTableQueries.insert(
                MyTable(UUID.randomUUID().toString())
            )
        }
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        database.transaction {
            database.myTableQueries.deleteAll()
        }
    }
}
