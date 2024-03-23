package com.vi.techshopmobile.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserOrderScreen(
    onNavigateUp: () -> Unit,
) {
    val viewModel: OrdersViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val token = LocalToken.current
    val statusList = mutableListOf("Đang xử lý", "Đã xử lý", "Đang giao hàng", "Hoàng thành","Đã hủy")

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(OrdersEvents.GetAllEvent(token))
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onLeftBtnClick = {},
                onRightBtnClick = {
                },
                leftBtnIcon = R.drawable.ic_left_arrow,
                title = "Đơn hàng",
            )
            {
//                onNavigateUp()
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()

        fun onStatusClick(status: String) {
            // Xử lý cho từng button
            when (status) {
                "Đang xử lý" -> {

                }
                "Đã xử lý" -> {
                    // TODO
                }
                "Đang giao hàng" -> {
                    // TODO
                }
                "Hoàn thành" -> {
                    // TODO
                }
                "Đã hủy" -> {
                    // TODO
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(top = topPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center

        ) {
            LazyRow(
                modifier = Modifier
                    .padding(bottom = topPadding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
                ){
                    items(statusList) { status ->
                        OutlinedButton(onClick ={ onStatusClick(status) }){Text(text = status)}
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = topPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cart_illustartion),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .width(272.dp)
                        .height(262.dp)
                )
                Text(text = "Hiện không có đơn hàng")
            }
        }
    }
}
