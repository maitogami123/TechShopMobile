package com.vi.techshopmobile.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.domain.model.Brand
import com.vi.techshopmobile.presentation.products.RowProductsComponent
import com.vi.techshopmobile.presentation.products.RowProductsItem

@Composable
fun CategoryComponent() {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth(),
//            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        state.categories.forEach { categories ->
            CategoriesBox(categoryName = categories.name)
            RowItemBrands(brands = categories.brands)
            RowProductsComponent()
        }

    }
}

@Composable
fun RowItemBrands(modifier: Modifier = Modifier, brands: List<Brand>) {
    LazyRow(
        modifier = modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        items(brands) { brands ->
            ItemBrand(brands.brandName)
        }
    }
}

@Composable
fun ItemBrand(nameBrand: String) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffC3DDFD)),
        onClick = { /*TODO*/ }) {
        Text(text = nameBrand, color = Color(0xff1A56DB))
    }
}

@Preview
@Composable
fun ItemBrandPreview() {
    ItemBrand("MSI")
}

@Preview
@Composable
fun RowItemBrandsPreview() {
    RowItemBrands(
        brands = listOf(
            Brand(id = 1, brandName = "MSI", products = emptyList()),
            Brand(id = 2, brandName = "ACER", products = emptyList()),
            Brand(id = 1, brandName = "MSI", products = emptyList())
        )
    )
}