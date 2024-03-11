package com.vi.techshopmobile.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.presentation.categories.CategoriesScreen
import com.vi.techshopmobile.presentation.categories.CategoryComponent
import com.vi.techshopmobile.presentation.products.RowProductsComponent
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 0.dp)
    ) {
        item {
            Column {
                CategoriesScreen()
                Column(modifier = Modifier) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.displayMedium,
                            textAlign = TextAlign.Center,
                            text = "Sản phẩm mới"
                        )
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = "Xem tất cả",
                                style = MaterialTheme.typography.displaySmall,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }
                RowProductsComponent()
                CategoryComponent()
            }
        }
        //Text(text = "HomeScreen")
//    ProductsScreen()

    }
}
@Preview()
@Composable
fun PreviewHomeScreen() {
    TechShopMobileTheme {
        //HomeScreen()
    }
}