package com.vi.techshopmobile.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.cart.CartResponse
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.cart.components.NoCartScreen
import com.vi.techshopmobile.presentation.cart.components.ProductCart
import com.vi.techshopmobile.presentation.cart.components.RowPrice
import com.vi.techshopmobile.presentation.cart.components.RowPriceDelivery
import com.vi.techshopmobile.presentation.cart.components.RowTotalPrice
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.SwipeToDeleteContainer
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.products.ProductsViewModel
import com.vi.techshopmobile.util.decodeToken

@Composable
fun CartScreen(
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: CartViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val decodedToken = decodeToken(LocalToken.current)

    val viewModelProduct: ProductsViewModel = hiltViewModel()
    val stateProduct by viewModelProduct.state.collectAsState()

    val totalPrice = viewModel.totalPrice.collectAsState()

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(CartEvent.GetUserCart(decodedToken.sub))
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = {
                    onNavigateUp()
                },
                leftBtnIcon = R.drawable.ic_left_arrow,
                title = "Giỏ hàng",
                onSearch = {})
        },
        bottomBar = {
            if (state.value.isNotEmpty())
                FloatingBottomBar(buttonText = "Thanh Toán", onButtonClick = {
                    if (state.value.isNotEmpty()) {
                        navController.navigate(Route.CheckOutScreenNavigation.route)
                    }
                })
        }
    )
    {
        if (state.value.isEmpty()) {
            NoCartScreen(navController = navController) {
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(it),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(.7f)
                        .padding(Dimens.SmallPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(state.value) { index, item ->
                        SwipeToDeleteContainer(item = item, onDelete = {
                            (viewModel::onEvent)(CartEvent.DeleteCart(item))
                        }) { item ->
                            ProductCart(
                                onPlusQuantity = {
                                    (viewModel::onEvent)(CartEvent.IncreaseItemToCart(item))
                                },
                                onMinusQuantity = {
                                    (viewModel::onEvent)(CartEvent.DecreaseItemToCart(item))
                                },
                                onDeleteProduct = {
                                    (viewModel::onEvent)(CartEvent.DeleteCart(item))
                                },
                                cartResponse = CartResponse(
                                    thumbnailUri = item.thumbnailUri,
                                    price = item.price,
                                    productName = item.productName,
                                    productLine = item.productLine,
                                    quantity = item.quantity,
                                    stock = item.stock
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider()
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.SmallPadding)
                ) {
                    Text(
                        text = "Chi tiết thanh toán",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                    Column(
                        modifier = Modifier.padding(Dimens.SmallPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        RowPrice(textLeft = "Tổng tiền hàng", textRight = totalPrice.value)
                        RowPriceDelivery(textLeft = "Tổng tiền vận chuyển", textRight = "Miễn Phí")
                        RowTotalPrice(textLeft = "Tổng thanh toán", textRight = totalPrice.value)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController()) {}
}