package com.example.myapplication.feature.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.data.ProductData
import kotlinx.serialization.json.jsonPrimitive

@Composable
fun ProductRoute(
    onBackClick: () -> Unit,
    viewModel: ProductViewModel = hiltViewModel()
) {
    ProductView(
        onBackClick = onBackClick,
        productData = viewModel.productData
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductView(
    onBackClick: () -> Unit,
    productData: ProductData
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(productData.code) },
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
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = productData.productJson["product_name"]?.jsonPrimitive?.content ?: "Name unavailable",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
