package com.vi.techshopmobile.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.presentation.common.ProductCard
import com.vi.techshopmobile.presentation.common.ShimmerListItem
import com.vi.techshopmobile.presentation.loading.LoadingDialog
import kotlinx.coroutines.delay

@Composable
fun ProductsScreen() {
    val viewModel: ProductsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    val state by viewModel.state.collectAsStateWithLifecycle()
//    var isLoading by remember {
//        mutableStateOf(true)
//    }
//
//    LaunchedEffect(key1 = true) {
//        delay(2000)
//        isLoading = false
//    }

//    DisposableEffect(key1 = true) {
//
//        isLoading = true
//        onDispose {
//            isLoading = false }
//    }

    //LoadingDialog(isLoading = state.isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { (viewModel::onEvent)(ProductsEvents.getOnEventProduct) },
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

