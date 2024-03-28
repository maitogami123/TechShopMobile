package com.vi.techshopmobile.presentation.checkout

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.cart.CartViewModel
import com.vi.techshopmobile.presentation.cart.components.CartEvent
import com.vi.techshopmobile.presentation.cart.components.ProductCart
import com.vi.techshopmobile.presentation.cart.components.RowPaymentGateNavigate
import com.vi.techshopmobile.presentation.cart.components.RowPaymentNavigate
import com.vi.techshopmobile.presentation.cart.components.RowTotalPrice
import com.vi.techshopmobile.presentation.common.AddressTitle
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.formatPhoneNumber
import com.vi.techshopmobile.util.decodeToken

@Composable
fun CheckOutScreen(
    navGraphController: NavController,
    navController: NavController,
    onNavigateUp: () -> Unit) {
    val viewModel: CartViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val token = LocalToken.current
    val decodedToken = decodeToken(token)

    val totalPrice = viewModel.totalPrice.collectAsState()

    val viewModelPersonalInfo: PersonalInfoViewModel = hiltViewModel()
    val statePersonalInfo = viewModelPersonalInfo.state.collectAsState()

    LaunchedEffect(key1 = null) {
        // TODO: Migrate event
        (viewModelPersonalInfo::onEvent)(PersonalInfoEvent.GetAllEventPersonalInfo(token))
    }
    LaunchedEffect(key1 = null) {
        // TODO: Migrate event
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
                title = "Thanh Toán",
                onSearch = {})
        },
        bottomBar = {
            FloatingBottomBar(buttonText = "Thanh Toán", onButtonClick = {})
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

            val personalInfo = statePersonalInfo.value.userDetail!!.accountDetail
            AddressTitle(
                title = "Địa chỉ nhận hàng",
                name = personalInfo.firstName + personalInfo.lastName,
                phoneNumber = formatPhoneNumber(personalInfo.phoneNumber),
                addressNote = personalInfo.detailedAddress,
                address = personalInfo.district,
                onEdit = {}
            )

            Spacer(modifier = Modifier.height(Dimens.SmallPadding))
            Divider()
            Spacer(modifier = Modifier.height(Dimens.SmallPadding))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RowPaymentNavigate(
                        textLeft = "Phương thức thanh toán",
                        textRight = "Thanh toán online"
                    )
                    RowPaymentGateNavigate(
                        textLeft = "Cổng thanh toán", textRight = "VN PAY", painterResource(
                            id = R.drawable.ic_bell
                        )
                    )
                    RowTotalPrice(textLeft = "Tổng thanh toán", textRight = totalPrice.value)
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