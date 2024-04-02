package com.vi.techshopmobile.presentation.order_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.cart.CartResponse
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.cart.components.ProductCart
import com.vi.techshopmobile.presentation.cart.components.RowPaymentGateNavigate
import com.vi.techshopmobile.presentation.cart.components.RowPaymentNavigate
import com.vi.techshopmobile.presentation.cart.components.RowTotalPrice
import com.vi.techshopmobile.presentation.checkout.LocalSelectedIndex
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.common.AddressTitle
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel
import com.vi.techshopmobile.util.formatPhoneNumber
import com.vi.techshopmobile.util.formatPrice

@Composable
fun OrderDetailsScreen(id: String, navController: NavController, onNavigateUp: () -> Unit) {

    val viewModel: OrderDetailsViewModel = hiltViewModel()
    val token = LocalToken.current
    val state = viewModel.state.collectAsState()
    val totalPrice = viewModel.totalPrice.collectAsState()
    LaunchedEffect(key1 = id) {
        (viewModel::onEvent)(OrderDetailsEvent.GetOrderDetail(token, id))
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Chi tiết đơn hàng",
                leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(Dimens.SmallPadding)
                .padding(it),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state.value.isLoading) {
                LoadingDialog(isLoading = state.value.isLoading)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(.7f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(state.value.orderDetailResponse!!.orderItems) { index, item ->
                        ProductCart(
                            cartResponse = CartResponse(
                                quantity = 1,
                                productLine = item.productLine,
                                productName = item.productName,
                                price = item.price,
                                thumbnailUri = item.productLine + "/" + item.productLine + ".jpg"
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider()
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.SmallPadding))
            Divider()
            Spacer(modifier = Modifier.height(Dimens.SmallPadding))

            if (state.value.orderDetailResponse != null) {
                val orderInfo =
                    state.value.orderDetailResponse!!.orderInformation
                AddressTitle(
                    title = "Địa chỉ nhận hàng",
                    name = orderInfo.fullname,
                    phoneNumber = formatPhoneNumber(orderInfo.phoneNumber),
                    addressNote = orderInfo.note,
                    address = orderInfo.address,
                )
            }
            Spacer(modifier = Modifier.height(Dimens.SmallPadding))
            Divider()
            Spacer(modifier = Modifier.height(Dimens.SmallPadding))

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
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    RowPaymentNavigate(
//                        textLeft = "Phương thức thanh toán",
//                        textRight = if (state.value.orderDetailResponse!!.orderStatus != null) "Tiền mặt"
//                        else if (state.value.orderDetailResponse!!.paymentStatus != null) "Chuyển khoản"
//                        else "",
////                        paymentMethod[paymentMethodState.intValue].second,
//                    )
//                    if (state.value.orderDetailResponse!!.paymentStatus != null) {
//                        RowPaymentGateNavigate(
//                            textLeft = "Cổng thanh toán",
//                            textRight = "VN Pay",
////                        paymentOnlineGate[paymentOnlineGateState.intValue].second,
//                            headIconGate = painterResource(
//                                R.drawable.ic_bell
//                                //paymentOnlineGateIconState.intValue
//                            ),
//                        )
//                    }
                    RowTotalPrice(
                        textLeft = "Tổng thanh toán",
                        textRight = totalPrice.value
                    )
                }
            }
        }
    }
}

