package com.vi.techshopmobile.presentation.products.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.common.ShimmerListItem

@Composable
fun ProductsColumn(modifier: Modifier = Modifier ,products: List<ProductLine>, isLoading: Boolean) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth().padding(16.dp).fillMaxHeight(),
    ) {
        items(products) { product ->
            ShimmerListItem(
                isLoading = isLoading,
                contentAfterLoading = {
                    ProductCard(product = product)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}