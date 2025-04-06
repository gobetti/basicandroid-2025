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
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeView(
    viewModel: HomeViewModel = HomeViewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Button(onClick = viewModel::onButtonClick) {
                Text("Insert")
            }

            val entries by viewModel.myTableEntries.collectAsStateWithLifecycle()

            entries.forEach { text ->
                Text(text)
            }
        }
    }
}
