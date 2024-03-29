package com.vi.techshopmobile.presentation.wish_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import arrow.core.none
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.order.component.OrdersItem
import com.vi.techshopmobile.ui.theme.Blue_500
import com.vi.techshopmobile.util.decodeToken

@Composable
fun WishListScreen(onNavigateUp: () -> Unit) {
    val viewModel: WishListViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val decodedToken = decodeToken(LocalToken.current)
    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(WishListEvents.GetUserWishList(decodedToken.sub))
    }

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
                .padding(top = it.calculateTopPadding())
        ) {
            Column {
                Text(text = "${decodedToken.sub}'s wishList")
                Divider()
                state.value.forEach {
                    Column() {
                        Text(text = it.productName)
                        Text(text = it.productLine)
                        Text(text = it.username)
                        Text(text = it.quantity.toString())
                    }
                    Divider()
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
                    Text(modifier = Modifier.padding(top=200.dp),
                        text = "Hiện tại bạn không có sản phẩm yêu thích nào")
                    OutlinedButton(modifier = Modifier.padding(top=300.dp),
                        colors = outlinedButtonColors(Color(0xFFB2EBF2)),
                        border = BorderStroke(
                            width = 0.dp,
                            color = Color.White
                        ),
                        onClick = { /*TODO*/ }) {
                        Text(text = "Khám phá với TechShop")
                    }
                }
            }
        }
    }
}