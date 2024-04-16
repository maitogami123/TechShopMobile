package com.vi.techshopmobile.presentation.products

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.Brand
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToFilter
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.products.component.ProductsColumn

@Composable
fun ProductsScreen(
    navController: NavController,
    categoryName: String,
    brandName: String,
    products: List<ProductLine>,
    isFilter: Boolean = false,
) {
    val viewModel: ProductsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val state by viewModel.state.collectAsStateWithLifecycle()

    val brands = ArrayList<Brand>()
    var productsShow = emptyList<ProductLine>()

    if (brandName.isEmpty()) {
        productsShow =
            state.productsOfCategory.find { it.category == categoryName }?.products ?: emptyList()
        brands.addAll(
            state.categories.filter { it.name == categoryName }.flatMap { it.brands }
                .map { it }
        )
    } else {
        state.categories.forEachIndexed { _, category ->
            if (category.name == categoryName) {
                for (brand in category.brands) {
                    if (brand.brandName == brandName) {
                        productsShow = brand.products
                    }
                }
            }
        }
        brands.addAll(state.categories.filter { it.name == categoryName }.flatMap { it.brands }
            .filter { it.brandName == brandName }.map { it })
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
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            UtilityTopNavigation(
                onRightBtnClick = {
                    navigateToFilter(
                        navController = navController,
                        category = categoryName,
                        brands = brands,
                        products = productsShow,
                    )
                },
                onLeftBtnClick = { navController.navigate(Route.HomeScreen.route) },
                rightBtnIcon = R.drawable.ic_filter,
                leftBtnIcon = R.drawable.ic_cross,
                title = "$categoryName $brandName",
                onSearch = {})
        }) {
            if (state.isLoading) {
                LoadingDialog(isLoading = state.isLoading)
            } else {
                Box(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                ) {
                    if (isFilter && products.isEmpty()) {
                        Text(text = "Hiện tại không có sản phẩm phù hợp")
                    } else if (isFilter) {
                        ProductsColumn(
                            modifier = Modifier,
                            products = products,
                            isLoading = isLoading
                        )
                    } else if (brandName.isNotBlank() && productsShow.isEmpty()) {
                        Text(text = "Hiện tại không có sản phẩm nào của $categoryName $brandName")
                    } else {
                        ProductsColumn(
                            modifier = Modifier,
                            products = productsShow,
                            isLoading = isLoading
                        )
                    }
                }
            }
        }
    }
}



