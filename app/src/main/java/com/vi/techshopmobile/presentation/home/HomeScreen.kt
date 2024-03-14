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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.presentation.categories.CategoriesSection
import com.vi.techshopmobile.presentation.categories.CategoryProducts
import com.vi.techshopmobile.presentation.products.ProductsEvents
import com.vi.techshopmobile.presentation.products.ProductsViewModel
import com.vi.techshopmobile.presentation.products.component.ProductsRow
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun HomeScreen() {
    val viewModel: ProductsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val state by viewModel.state.collectAsStateWithLifecycle()

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { (viewModel::onEvent)(ProductsEvents.GetAllEventProduct) },
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.White,
                contentColor = Color.DarkGray
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 0.dp)
        ) {
            item {
                Column {
                    CategoriesSection()
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
                    ProductsRow(state.products)
                    CategoryProducts()
                }
            }
        }
    }

}

@Preview()
@Composable
fun PreviewHomeScreen() {
    TechShopMobileTheme {
        //HomeScreen()
    }
}