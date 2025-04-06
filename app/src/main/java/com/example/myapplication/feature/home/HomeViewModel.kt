package com.example.myapplication.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.myapplication.DI
import com.example.myapplication.DI.database
import com.example.myapplication.Database
import com.example.myapplication.MyTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class HomeViewModel(
    database: Database = DI.database
) : ViewModel() {
    private val myTableFlow =
        database
            .myTableQueries
            .findAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
    val myTableEntries = myTableFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = emptyList()
    )

    fun onButtonClick() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            database.transaction {
                database.myTableQueries.insert(
                    MyTable(UUID.randomUUID().toString())
                )
            }
        }
    }
}
