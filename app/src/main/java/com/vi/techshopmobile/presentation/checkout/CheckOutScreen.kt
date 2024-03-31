package com.vi.techshopmobile.presentation.checkout

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.cart.CartResponse
import com.vi.techshopmobile.data.remote.orders.dto.CartItem
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.domain.usecases.orders.CreateOrders
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.cart.CartViewModel
import com.vi.techshopmobile.presentation.cart.CartEvent
import com.vi.techshopmobile.presentation.cart.components.ProductCart
import com.vi.techshopmobile.presentation.cart.components.RowPaymentGateNavigate
import com.vi.techshopmobile.presentation.cart.components.RowPaymentNavigate
import com.vi.techshopmobile.presentation.cart.components.RowTotalPrice
import com.vi.techshopmobile.presentation.checkout.components.PaymentOption
import com.vi.techshopmobile.presentation.common.AddressTitle
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.formatPhoneNumber
import com.vi.techshopmobile.util.decodeToken


@Composable
fun CheckOutScreen(
    navGraphController: NavController,
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: CheckOutViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val token = LocalToken.current
    val decodedToken = decodeToken(token)
    val paymentMethod = listOf("online" to "Chuyển khoản", "cash" to "Tiền mặt")
    val paymentOnlineGate = listOf("ic_cast" to "VN Pay", "ic_money" to "Momo")
    val totalPrice = viewModel.totalPrice.collectAsState()

    val statePersonalInfo = viewModel.statePerson.collectAsState()
    val isCreateOrder = viewModel.isCreateOrder.collectAsState()

    val paymentMethodIsExpanded = remember {
        mutableStateOf(false);
    }

    val paymentOnlineGateIsExpanded = remember {
        mutableStateOf(false);
    }

    val paymentMethodState = remember {
        mutableIntStateOf(0)
    }

    val paymentOnlineGateState = remember {
        mutableIntStateOf(0)
    }

    val paymentOnlineGateIconState = remember {
        mutableIntStateOf(R.drawable.ic_cast)
    }

    LaunchedEffect(key1 = null) {
        // TODO: Migrate event
        if (statePersonalInfo.value.listUserDetail.isEmpty()) {
            (viewModel::onEvent)(CheckOutEvent.GetListUserDetail(token))
        }
    }
    LaunchedEffect(key1 = null) {
        // TODO: Migrate event
        (viewModel::onEvent)(CheckOutEvent.GetUserCart(decodedToken.sub))
    }

    LaunchedEffect(key1 = isCreateOrder.value) {
        if (isCreateOrder.value) {
            navController.navigate(Route.PaymentSuccessnScreen.route)
        }
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = {
                    onNavigateUp()
                },
                leftBtnIcon = R.drawable.ic_left_arrow,
                title = "Thanh Toán",
                onSearch = {})
        },
        bottomBar = {
            if (statePersonalInfo.value.listUserDetail.isNotEmpty()) {
                val personalInfo =
                    statePersonalInfo.value.listUserDetail[LocalSelectedIndex.current.intValue].accountDetail
                Log.d("IND", LocalSelectedIndex.current.intValue.toString())
                FloatingBottomBar(
                    buttonText = "Thanh Toán",
                    onButtonClick = {
                        if (paymentMethodState.intValue == 1) {
                            (viewModel::onEvent)(
                                CheckOutEvent.createOrders(
                                    token = token,
                                    requestCheckOut = RequestCheckOut(
                                        email = personalInfo.email,
                                        phoneNumber = personalInfo.phoneNumber,
                                        lastName = personalInfo.lastName,
                                        firstName = personalInfo.firstName,
                                        district = personalInfo.district,
                                        detailedAddress = personalInfo.detailedAddress,
                                        cartItems =
                                        state.value.map { item ->
                                            CartItem(
                                                productLine = item.productLine,
                                                quantity = item.quantity
                                            )
                                        },
                                        city = personalInfo.city,
                                        total = totalPrice.value.toInt()
                                    ),
                                )
                            )
                        }
                    }
                )
            }
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(Dimens.SmallPadding)
                .padding(it),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (statePersonalInfo.value.isLoading) {
                LoadingDialog(isLoading = statePersonalInfo.value.isLoading)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(.7f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(state.value) { index, item ->
                        ProductCart(
                            cartResponse = CartResponse(
                                thumbnailUri = item.thumbnailUri,
                                price = item.price,
                                productName = item.productName,
                                productLine = item.productLine,
                                quantity = item.quantity,
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider()
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                Divider()
                Spacer(modifier = Modifier.height(Dimens.SmallPadding))

                if (statePersonalInfo.value.listUserDetail.isNotEmpty()) {
                    val personalInfo =
                        statePersonalInfo.value.listUserDetail[LocalSelectedIndex.current.intValue].accountDetail
                    AddressTitle(
                        title = "Địa chỉ nhận hàng",
                        name = personalInfo.firstName + " " + personalInfo.lastName,
                        phoneNumber = formatPhoneNumber(personalInfo.phoneNumber),
                        addressNote = personalInfo.detailedAddress,
                        address = personalInfo.district,
                        onEdit = {
                            navController.navigate(Route.ListInfoScreen.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                Divider()
                Spacer(modifier = Modifier.height(Dimens.SmallPadding))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .animateContentSize()
//                            .clickable {
//                                paymentMethodIsExpanded.value = !paymentMethodIsExpanded.value
//                            }
                        ) {
                            RowPaymentNavigate(
                                textLeft = "Phương thức thanh toán",
                                textRight = paymentMethod[paymentMethodState.intValue].second,
                                onClick = {
                                    paymentMethodIsExpanded.value =
                                        !paymentMethodIsExpanded.value
                                }
                            )
                            if (paymentMethodIsExpanded.value) {
                                Column {
                                    PaymentOption(
                                        title = paymentMethod[0].second,
                                        headIcon = painterResource(id = R.drawable.ic_cast),
                                        isChecked = paymentMethodState.intValue == 0,
                                        onClick = {
                                            paymentMethodState.intValue = 0
                                            paymentMethodIsExpanded.value = false
                                        }
                                    )
                                    PaymentOption(
                                        title = paymentMethod[1].second,
                                        headIcon = painterResource(id = R.drawable.ic_money),
                                        isChecked = paymentMethodState.intValue == 1,
                                        onClick = {
                                            paymentMethodState.intValue = 1
                                            paymentMethodIsExpanded.value = false
                                        }
                                    )
                                }
                            }

                        }
                        if (paymentMethodState.intValue == 0) {
                            Column(
                                modifier = Modifier
                                    .animateContentSize()
                            ) {
                                RowPaymentGateNavigate(
                                    textLeft = "Cổng thanh toán",
                                    textRight = paymentOnlineGate[paymentOnlineGateState.intValue].second,
                                    headIconGate = painterResource(
                                        paymentOnlineGateIconState.intValue
                                    ),
                                    onClick = {
                                        paymentOnlineGateIsExpanded.value =
                                            !paymentOnlineGateIsExpanded.value
                                    }
                                )

                                if (paymentOnlineGateIsExpanded.value) {
                                    PaymentOption(
                                        title = paymentOnlineGate[0].second,
                                        headIcon = painterResource(id = R.drawable.ic_cast),
                                        isChecked = paymentOnlineGateState.intValue == 0,
                                        onClick = {
                                            paymentOnlineGateState.intValue = 0
                                            paymentOnlineGateIconState.intValue =
                                                R.drawable.ic_cast
                                            paymentOnlineGateIsExpanded.value = false
                                        }
                                    )
                                    PaymentOption(
                                        title = paymentOnlineGate[1].second,
                                        headIcon = painterResource(id = R.drawable.ic_money),
                                        isChecked = paymentOnlineGateState.intValue == 1,
                                        onClick = {
                                            paymentOnlineGateState.intValue = 1
                                            paymentOnlineGateIconState.intValue =
                                                R.drawable.ic_money
                                            paymentOnlineGateIsExpanded.value = false
                                        }
                                    )
                                }
                            }
                        }
                        RowTotalPrice(
                            textLeft = "Tổng thanh toán",
                            textRight = totalPrice.value
                        )
                    }
                }
            }
        }

    }
}


@Preview
@Composable
fun CheckOutScreenPreview() {
    TechShopMobileTheme {
//        CheckOutScreen {
//
//        }
    }
}