package com.vi.techshopmobile.presentation.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.domain.model.Brand
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToFilter
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.products.component.ProductsColumn


@Composable
fun SearchResultScreen(
    navController: NavController,
    searchQuery: String,
    productsFilter: List<ProductLine>,
    isFilter: Boolean = false,
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val productsSearch = viewModel.productsSearch.collectAsState()

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(SearchEvent.GetProductSearch(SearchProduct(search = searchQuery)))
    }
    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = {
                    navigateToFilter(
                        navController = navController,
                        categoryName = searchQuery,
                        brands = ArrayList<Brand>(),
                        products = productsSearch.value.productsSearch,
                        isSearch = true
                    )
                },
                onLeftBtnClick = { navController.navigate(Route.SearchScreen.route) },
                leftBtnIcon = R.drawable.ic_left_arrow,
                rightBtnIcon = R.drawable.ic_filter,
                titleSearch = searchQuery,
                onSearch = {})
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Box(modifier = Modifier.padding(top = topPadding)) {
            if (isFilter && productsFilter.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 67.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painterResource(id = R.drawable.error),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                        Text(
                            text = "Hiện không có sản phẩm phù hợp!",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight(500),
                            ),
                            textAlign = TextAlign.Center
                        )
                    }

                }
            } else if (isFilter) {
                ProductsColumn(
                    products = productsFilter,
                    isLoading = false
                )
            } else ProductsColumn(
                products = productsSearch.value.productsSearch,
                isLoading = productsSearch.value.isLoading
            )
        }
    }
}