package com.example.myapplication.feature.mylist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.Serializable

@Serializable
data object MyList

@Composable
fun MyListRoute(
    viewModel: MyListViewModel = hiltViewModel()
) {
    MyListView(
        onInsert = viewModel::insert,
        onClear = viewModel::clear,
        items = viewModel.databaseSource.currentState()
    )
}

@Composable
fun MyListView(
    onInsert: () -> Unit,
    onClear: () -> Unit,
    items: List<String>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            controlsItem(
                onInsert = onInsert,
                onClear = onClear
            )

            databaseItems(items = items)
        }
    }
}

private fun LazyListScope.controlsItem(
    onInsert: () -> Unit,
    onClear: () -> Unit
) {
    item(
        key = "Controls",
        contentType = "Controls"
    ) {
        Button(onClick = onInsert) {
            Text("Insert")
        }

        Button(onClick = onClear) {
            Text("Clear")
        }
    }
}

private fun LazyListScope.databaseItems(items: List<String>) {
    items(
        count = items.size,
        key = { index -> items[index] },
        contentType = { "DatabaseItem" }
    ) { index ->
        Text(items[index])
    }
}
