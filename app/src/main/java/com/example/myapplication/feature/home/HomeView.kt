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

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = HomeViewModel()
) {
    val entries by viewModel.myTableEntries.collectAsStateWithLifecycle()
    HomeView(
        onButtonClick = viewModel::onButtonClick,
        entries = entries
    )
}

@Composable
private fun HomeView(
    onButtonClick: () -> Unit,
    entries: List<String>
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Button(onClick = onButtonClick) {
                Text("Insert")
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
        entries = listOf("Entry 1", "Entry 2")
    )
}
