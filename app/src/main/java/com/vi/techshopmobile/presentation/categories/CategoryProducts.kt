package com.vi.techshopmobile.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vi.techshopmobile.presentation.categories.components.CategoriesBanner
import com.vi.techshopmobile.presentation.products.component.ProductsRow

@Composable
fun CategoryProducts() {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        state.categories.forEachIndexed { index, category ->
            if (state.categoriesProduct.isNotEmpty() && state.categoriesProduct.size > index && state.categoriesProduct[index].products.isNotEmpty()) {
                CategoriesBanner(categoryName = category.name)
                LazyRow(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    items(category.brands) { brand ->
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffC3DDFD)),
                            onClick = { /*TODO*/ }) {
                            Text(text = brand.brandName, color = Color(0xff1A56DB))
                        }
                    }
                }
                ProductsRow(products = state.categoriesProduct[index].products)
            }
        }
    }
}
