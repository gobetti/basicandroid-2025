package com.example.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.data.ProductData
import kotlinx.serialization.json.JsonObject

@Composable
internal fun HomeView(
    onAction: (HomeAction) -> Unit,
    state: HomeViewState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            Foods(
                barcode = state.barcode,
                onBarcodeChange = { onAction(HomeAction.BarcodeChange(it)) },
                onSearchClick = { onAction(HomeAction.Search) },
                onSearchResultClick = { onAction(HomeAction.OpenProduct(it)) },
                searchResult = state.searchResult
            )

            Controls(
                onButtonClick = { onAction(HomeAction.OpenList) },
                onResetClick = { onAction(HomeAction.Reset) },
                allTimeCounter = state.allTimeCounter,
                counter = state.counter
            )
        }
    }
}

@Composable
private fun ColumnScope.Foods(
    barcode: String,
    onBarcodeChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onSearchResultClick: (ProductData) -> Unit,
    searchResult: ProductData?
) {
    OutlinedTextField(
        value = barcode,
        onValueChange = onBarcodeChange
    )

    Button(onClick = onSearchClick) {
        Text("Search")
    }

    if(searchResult != null) {
        ListItem(
            headlineContent = {
                Text(searchResult.code)
            },
            modifier = Modifier.clickable {
                onSearchResultClick(searchResult)
            }
        )
    }
}

@Composable
private fun ColumnScope.Controls(
    onButtonClick: () -> Unit,
    onResetClick: () -> Unit,
    allTimeCounter: Int,
    counter: Int
) {
    Button(
        onClick = onButtonClick
    ) {
        Text("Go to List")
    }

    Button(onClick = onResetClick) {
        Text("Reset")
    }

    Text("All time counter: $allTimeCounter")

    Text("This session's counter: $counter")
}

@Preview
@Composable
private fun HomePreview() {
    HomeView(
        onAction = {},
        state = HomeViewState(
            isLoading = false,
            barcode = "034270",
            searchResult = ProductData(
                code = "3824905",
                productJson = JsonObject(emptyMap())
            ),
            allTimeCounter = 1,
            counter = 0
        )
    )
}
