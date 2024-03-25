package com.vi.techshopmobile.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.OrderItem
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.common.ShimmerListItem
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.order.component.OrdersItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    onNavigateUp: () -> Unit,
) {
    val viewModel: OrdersViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val filteredOrders = remember { mutableStateOf(state.orders) };
    val token = LocalToken.current
    val statusList =
        listOf("Tất cả", "Đang xử lý", "Đã xử lý", "Đang giao hàng", "Hoàn thành", "Đã hủy")
    val selectedItem = remember {
        mutableStateOf(0)
    }


    LaunchedEffect(key1 = state.orders) {
        if (state.orders.isEmpty()) {
            (viewModel::onEvent)(OrdersEvents.GetAllEvent(token))
        }
    }

    LaunchedEffect(key1 = selectedItem) {
        if (selectedItem.value == 1) {
            filteredOrders.value = filteredOrders.value.filter { order -> order.status == "PENDING" }
        }
    }

    LaunchedEffect(key1 = state.isLoading) {
        if (state.isLoading == false) {
            filteredOrders.value = state.orders
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
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(statusList) { status ->
                    OutlinedButton(onClick = { selectedItem.value = 1 }) {
                        Text(
                            text = status
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.padding(horizontal = SmallPadding)
                //            verticalArrangement = Arrangement.spacedBy(8.dp),
            )
            {
                items(filteredOrders.value) { order ->
                    OrdersItem(order = order) {

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
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Hiện không có đơn hàng")
                        }
                    }
                }
            }
        }
    }
    LoadingDialog(isLoading = state.isLoading)

//    Scaffold(
//        topBar = {
//            UtilityTopNavigation(
//                onLeftBtnClick = {},
//                onRightBtnClick = {
//                },
//                leftBtnIcon = R.drawable.ic_left_arrow,
//                title = "Đơn hàng",
//            )
//            {
////                onNavigateUp()
//            }
//        }
//
//    ) {
//        val topPadding = it.calculateTopPadding()
//
//        fun onStatusClick(status: String) {
//            viewModel.filterOrdersByStatus(status)
//        }
//        Column (
//            modifier = Modifier
//                .padding(top = topPadding)
//                .verticalScroll(rememberScrollState())
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center
//
//        ) {
//            LazyRow(
//                modifier = Modifier
//                    .padding(bottom = topPadding)
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
//                verticalAlignment = Alignment.Top,
//                ){
//                    items(statusList) { status ->
//                        OutlinedButton(onClick ={ onStatusClick(status) }){Text(text = status)}
//                }
//            }
//            Box(
//                modifier = Modifier
//                    .padding(top = topPadding)
//                    .fillMaxSize(),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.cart_illustartion),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(bottom = 50.dp)
//                        .width(272.dp)
//                        .height(262.dp)
//                )
//
//                Text(text = "Hiện không có đơn hàng")
//            }
//        }
//    }
}
