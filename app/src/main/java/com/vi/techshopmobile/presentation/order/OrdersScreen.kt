package com.vi.techshopmobile.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.checkout.navigateToDetailOrder
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.order.component.OrdersItem
import com.vi.techshopmobile.ui.theme.Blue_500

@Composable
fun UserOrdersScreen(
    onNavigateUp: () -> Unit,
    navController: NavController
) {

    val viewModel: OrdersViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val token = LocalToken.current
    val statusList =
        listOf(
            mapOf("ALL" to "Tất cả"),
            mapOf("PENDING" to "Đang xử lý"),
            mapOf("CONFIRMED" to "Đã xác nhận"),
            mapOf("DELIVERING" to "Đang giao hàng"),
            mapOf("SUCCESS" to "Hoàn thành"),
            mapOf("CANCELED" to "Đã hủy"),
        )
    val selectedItem = remember {
        mutableStateOf("ALL")
    }



    LaunchedEffect(key1 = state.orders) {
        if (state.orders.isEmpty()) {
            (viewModel::onEvent)(OrdersEvents.GetAllEvent(token))
        }
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Đơn hàng",
                leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            LazyRow(
                modifier = Modifier.padding(start = SmallPadding),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(statusList) { status ->
                    for ((key, value) in status) {
                        OutlinedButton(
                            onClick = { selectedItem.value = key },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedItem.value == key) Blue_500 else Color.Transparent,
                                contentColor = if (selectedItem.value == key) Color.White else MaterialTheme.colorScheme.primary
                            ),
                            border = if (selectedItem.value == key) null else ButtonDefaults.outlinedButtonBorder
                        ) {
                            Text(
                                text = value,
//                                color = if (value == "CANCELED") Color(0xffFF3A28)
//                                else if (value == "SUCCESS") Color(0xff53B175)
//                                else if (value == "CONFIRMED") Color(0xff3FA4FC)
//                                else if (value == "PENDING") Color(0xffFFBB32)
//                                else if (value == "DELIVERING") Color(0xff1e91cf)
//                                else Color(0xffe3d4d4)
                            )
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.padding(horizontal = SmallPadding)
            )
            {
                var counter = 0;
                items(state.orders) { order ->
                    if (selectedItem.value == "ALL") {
                        OrdersItem(order = order) {
                            navigateToDetailOrder(navController, order.id.toString())
                        }
                        counter += 1;
                    } else if (order.status == selectedItem.value) {
                        OrdersItem(order = order) {
                            navigateToDetailOrder(navController, order.id.toString())
                        }
                        counter += 1;
                    }
                }

                if (state.error != null) {
                    item {
                        // Hiển thị thông báo lỗi tại đây
                    }
                }

                if (state.orders.isEmpty() && !state.isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .padding(top = it.calculateTopPadding())
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cart_illustartion),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(bottom = 40.dp)
                                    .width(272.dp)
                                    .height(262.dp)
                            )
                            Text(text = "Hiện tại không có đơn hàng")
                        }
                    }
                }
            }
        }
    }
    LoadingDialog(isLoading = state.isLoading)
}