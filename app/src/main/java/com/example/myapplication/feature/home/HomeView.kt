package com.example.myapplication.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel()
) {
    val entries by viewModel.myTableEntries.collectAsStateWithLifecycle()
    val allTimeCounter by viewModel.allTimeCounter.collectAsStateWithLifecycle()
    HomeView(
        barcode = viewModel.barcode,
        onBarcodeChange = viewModel::onBarcodeChange,
        onSearchClick = viewModel::search,
        searchResult = viewModel.searchResult,
        onButtonClick = viewModel::insert,
        onClearClick = viewModel::clear,
        entries = entries,
        allTimeCounter = allTimeCounter,
        counter = viewModel.counter
    )
}

@Composable
private fun HomeView(
    barcode: String,
    onBarcodeChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    searchResult: String?,
    onButtonClick: () -> Unit,
    onClearClick: () -> Unit,
    entries: List<String>,
    allTimeCounter: Int,
    counter: Int
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                value = barcode,
                onValueChange = onBarcodeChange
            )

            Button(onClick = onSearchClick) {
                Text("Search")
            }

            searchResult?.let { searchResult ->
                Text(searchResult)
            }

            Button(onClick = onButtonClick) {
                Text("Insert")
            }

            Text("All time counter: $allTimeCounter")

            Text("This session's counter: $counter")

            Button(onClick = onClearClick) {
                Text("Clear")
            }

            entries.forEach { text ->
                Text(text)
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeView(
        barcode = "034270",
        onBarcodeChange = {},
        onSearchClick = {},
        searchResult = "3824905",
        onButtonClick = {},
        onClearClick = {},
        entries = listOf("Entry 1", "Entry 2"),
        allTimeCounter = 1,
        counter = 0
    )
}
