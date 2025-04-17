package com.example.myapplication.feature.mylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListRoute(
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
    viewModel: MyListViewModel = hiltViewModel()
) {
    MyListView(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        items = viewModel.databaseSource.currentState()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListView(
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
    items: List<String>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("My List") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            databaseItems(
                onItemClick = onItemClick,
                items = items
            )
        }
    }
}

private fun LazyListScope.databaseItems(
    onItemClick: (String) -> Unit,
    items: List<String>
) {
    items(
        count = items.size,
        key = { index -> items[index] },
        contentType = { "DatabaseItem" }
    ) { index ->
        val item = items[index]
        ListItem(
            headlineContent = {
                Text(item)
            },
            modifier = Modifier.clickable(onClick = { onItemClick(item) })
        )
    }
}
