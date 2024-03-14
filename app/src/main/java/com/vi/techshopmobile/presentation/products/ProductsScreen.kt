package com.vi.techshopmobile.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.presentation.products.component.ProductCard
import com.vi.techshopmobile.presentation.common.ShimmerListItem

@Composable
fun ProductsScreen() {
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
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = { }
        ) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.padding(top = it.calculateTopPadding()),
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp
            ) {
                items(state.products) { product ->
//                    ProductCard(product = product)
                    ShimmerListItem(
                        isLoading = state.isLoading,
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
    }
}

