package com.vi.techshopmobile.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.common.ProductCard

@Composable
fun RowProductsItem(modifier: Modifier = Modifier, products: List<ProductLine>) {
    LazyRow(
        modifier = modifier.padding(bottom = 28.dp, top = 20.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RowProductsItemPreview() {
    RowProductsItem(modifier = Modifier,
        products = listOf(
            ProductLine(
                brandName = "",
                id = 1,
                productLine = "",
                categoryName = "ssdsd",
                productName = "ABC",
                discount = 2.0,
                price = 19000.0,
                stock = 2,
                thumbnailUri = "/abc",
                createdAt = "",
                deletedAt = ""
            ), ProductLine(
                brandName = "",
                id = 2,
                productLine = "",
                categoryName = "",
                productName = "CDE",
                discount = 2.0,
                price = 19000.0,
                stock = 2,
                thumbnailUri = "",
                createdAt = "",
                deletedAt = ""
            )
        )
    )
}