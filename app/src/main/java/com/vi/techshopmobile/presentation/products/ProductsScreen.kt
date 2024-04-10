package com.vi.techshopmobile.presentation.products

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.common.ShimmerListItem
import com.vi.techshopmobile.presentation.home.home_navigator.LocalNavController
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToFilter
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.products.component.ProductCard
import com.vi.techshopmobile.presentation.products.component.ProductsColumn

@Composable
fun ProductsScreen(
    navController: NavController,
    categoryName: String,
    brandName: String,
    onNavigateUp: () -> Unit
) {
    val viewModel: ProductsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val state by viewModel.state.collectAsStateWithLifecycle()

    val brands = ArrayList<String>()
    if (brandName.isEmpty()) {
        state.categories.filter { category -> category.name == categoryName }
            .flatMap { category -> category.brands }
            .forEach { brand ->
                brands.add(brand.brandName)
            }
    }
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { (viewModel::onEvent)(ProductsEvents.GetAllProductByCategory(categoryName)) },
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
            topBar = {
                UtilityTopNavigation(
                    onRightBtnClick = { navigateToFilter(navController,brands) },
                    onLeftBtnClick = { onNavigateUp() },
                    rightBtnIcon = R.drawable.ic_filter,
                    leftBtnIcon = R.drawable.ic_cross,
                    title = "$categoryName $brandName",
                    onSearch = {})
            }
        ) {
            Box(
                modifier = Modifier.padding(top = it.calculateTopPadding()),
            ) {
                if (brandName.isEmpty()) {
                    val filteredProducts =
                        state.productsOfCategory.find { it.category == categoryName }?.products
                            ?: emptyList()
                    ProductsColumn(
                        modifier = Modifier,
                        products = filteredProducts,
                        isLoading = isLoading
                    )
                } else {
                    state.categories.forEachIndexed { _ , category ->
                        if (category.name == categoryName) {
                            for (brand in category.brands) {
                                if (brand.brandName == brandName) {
                                    ProductsColumn(
                                        modifier = Modifier,
                                        products = brand.products,
                                        isLoading = isLoading
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


