package com.vi.techshopmobile.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vi.techshopmobile.presentation.authenticate.sign_in.SignInScreen
import com.vi.techshopmobile.presentation.categories.CategoriesSection
import com.vi.techshopmobile.presentation.categories.CategoryProducts
import com.vi.techshopmobile.presentation.chatAI.ChatAiScreen
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.products.ProductsEvents
import com.vi.techshopmobile.presentation.products.ProductsViewModel
import com.vi.techshopmobile.presentation.products.component.ProductsRow
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: ProductsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(bottomBar = {}, floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(Route.CustomerSupportScreen.route) }
        ) {
            Icon(Icons.Filled.Face, contentDescription = "")
        }
    }) {
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
                    .padding(bottom = it.calculateBottomPadding())
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
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview()
@Composable
fun PreviewHomeScreen() {
    val viewModel: ProductsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val state by viewModel.state.collectAsStateWithLifecycle()
    TechShopMobileTheme {
        Scaffold(bottomBar = {}, floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Face, contentDescription = null)
            }
        }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding())
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
                        //ProductsRow(state.products)
                        //CategoryProducts()
                    }
                }
            }
        }
    }
}