package com.example.myapplication.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
        onButtonClick = viewModel::insert,
        onClearClick = viewModel::clear,
        entries = entries,
        allTimeCounter = allTimeCounter,
        counter = viewModel.counter
    )
}

@Composable
private fun HomeView(
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
        onButtonClick = {},
        onClearClick = {},
        entries = listOf("Entry 1", "Entry 2"),
        allTimeCounter = 1,
        counter = 0
    )
}
