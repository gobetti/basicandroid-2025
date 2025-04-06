package com.example.myapplication.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import com.example.myapplication.data.Product

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    HomeView(
        onAction = { action ->
            when(action) {
                is HomeAction.BarcodeChange -> viewModel.onBarcodeChange(action.barcode)
                HomeAction.Clear -> viewModel.clear()
                HomeAction.Insert -> viewModel.insert()
                HomeAction.Search -> viewModel.search()
            }
        },
        state = state
    )
}

@Composable
private fun HomeView(
    onAction: (HomeAction) -> Unit,
    state: HomeViewState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            foodsItem(
                barcode = state.barcode,
                onBarcodeChange = { onAction(HomeAction.BarcodeChange(it)) },
                onSearchClick = { onAction(HomeAction.Search) },
                searchResult = state.searchResult
            )

            controlsItem(
                onButtonClick = { onAction(HomeAction.Insert) },
                onClearClick = { onAction(HomeAction.Clear) },
                allTimeCounter = state.allTimeCounter,
                counter = state.counter
            )

            databaseItems(items = state.entries)
        }
    }
}

private fun LazyListScope.foodsItem(
    barcode: String,
    onBarcodeChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    searchResult: Product?
) {
    item(
        key = "Foods",
        contentType = "Foods"
    ) {
        OutlinedTextField(
            value = barcode,
            onValueChange = onBarcodeChange
        )

        Button(onClick = onSearchClick) {
            Text("Search")
        }

        searchResult?.let { searchResult ->
            Text(searchResult.code)
        }
    }
}

private fun LazyListScope.controlsItem(
    onButtonClick: () -> Unit,
    onClearClick: () -> Unit,
    allTimeCounter: Int,
    counter: Int
) {
    item(
        key = "Controls",
        contentType = "Controls"
    ) {
        Button(onClick = onButtonClick) {
            Text("Insert")
        }

        Text("All time counter: $allTimeCounter")

        Text("This session's counter: $counter")

        Button(onClick = onClearClick) {
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

@Preview
@Composable
private fun HomePreview() {
    HomeView(
        onAction = {},
        state = HomeViewState(
            barcode = "034270",
            searchResult = Product(code = "3824905"),
            entries = listOf("Entry 1", "Entry 2"),
            allTimeCounter = 1,
            counter = 0
        )
    )
}
