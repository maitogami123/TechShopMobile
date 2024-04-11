package com.vi.techshopmobile.presentation.wish_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import arrow.core.none
import coil.compose.AsyncImage
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.cart.CartResponse
import com.vi.techshopmobile.data.remote.orders.dto.OrderDetailResponse
import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.cart.components.ProductCart
import com.vi.techshopmobile.presentation.cart.components.ProductCartRow
import com.vi.techshopmobile.presentation.cart.components.ProductCartRowNoQuantity
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToDetails
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.order.component.OrdersItem
import com.vi.techshopmobile.presentation.product_details.ProductDetailsEvent
import com.vi.techshopmobile.presentation.product_details.ProductDetailsViewModel
import com.vi.techshopmobile.presentation.products.ProductsViewModel
import com.vi.techshopmobile.ui.theme.Blue_500
import com.vi.techshopmobile.util.Constants
import com.vi.techshopmobile.util.decodeToken
import com.vi.techshopmobile.util.formatPrice

@Composable
fun WishListScreen(onNavigateUp: () -> Unit, navController: NavController) {
    val viewModel: WishListViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val stateDetailProduct = viewModel.productDetail.collectAsState()

    val decodedToken = decodeToken(LocalToken.current)
    val productDetail = remember {
        mutableListOf<ProductDetailResponse>()
    }

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(WishListEvents.GetUserWishList(decodedToken.sub))
    }

//    LaunchedEffect(key1 = stateDetailProduct.value){
//        stateDetailProduct.value.forEach {
//            productDetail.plus(it)
//        }
//    }


    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Sản phẩm yêu thích",
                leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = Dimens.SmallPadding)
        ) {
            LazyColumn {
                itemsIndexed(stateDetailProduct.value) { index, item ->
                    ProductCartRowNoQuantity(
                        modifier = Modifier.clickable {
                            navigateToDetails(navController, item.product.productLine)
                        },
                        cartResponse = CartResponse(
                            productLine = item.product.productLine,
                            productName = item.product.productName,
                            price = item.product.price,
                            thumbnailUri = item.thumbnailUri
                        ),
                    )
                    Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                    Divider()
                    Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                }
            }
            if (state.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = it.calculateTopPadding()),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cart_illustartion),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 100.dp)
                            .width(272.dp)
                            .height(262.dp)
                    )
                    Text(
                        modifier = Modifier.padding(top = 200.dp),
                        text = "Hiện tại bạn không có sản phẩm yêu thích nào"
                    )
                    OutlinedButton(modifier = Modifier.padding(top = 300.dp),
                        colors = outlinedButtonColors(Color(0xFFB2EBF2)),
                        border = BorderStroke(
                            width = 0.dp,
                            color = Color.White
                        ),
                        onClick = { navController.navigate(Route.HomeScreen.route) }) {
                        Text(text = "Khám phá với TechShop")
                    }
                }
            }
        }
    }
}