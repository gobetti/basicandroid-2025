package com.example.feature.product

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.di.applicationComponent
import com.example.feature.product.di.ProductViewModelFactory
import com.example.feature.product.di.create

@Composable
internal fun ProductRoute(
    onBackClick: () -> Unit,
    viewModel: ProductViewModel = viewModel(factory = ProductViewModelFactory::class.create(LocalContext.current.applicationComponent))
) {
    val product by viewModel.product.collectAsStateWithLifecycle()
    ProductView(
        onBackClick = onBackClick,
        product = product
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductView(
    onBackClick: () -> Unit,
    product: ProductViewModel.DisplayableProduct
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(product.barcode) },
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
                text = product.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
