package com.vi.techshopmobile.presentation.checkout.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.vi.techshopmobile.presentation.cart.components.ProductCart
import com.vi.techshopmobile.presentation.cart.components.RowPrice
import com.vi.techshopmobile.presentation.cart.components.RowPriceDelivery
import com.vi.techshopmobile.presentation.cart.components.RowTotalPrice
import com.vi.techshopmobile.presentation.checkout.CheckOutEvent
import com.vi.techshopmobile.presentation.checkout.CheckOutViewModel
import com.vi.techshopmobile.presentation.checkout.LocalSelectedIndex
import com.vi.techshopmobile.presentation.common.AddressSelected
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.FloatingOnBottomBar
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel
import com.vi.techshopmobile.util.formatPhoneNumber

@Composable
fun ListInfoScreen(
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: CheckOutViewModel = hiltViewModel()
    val state = viewModel.statePerson.collectAsState()
    val token = LocalToken.current

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(CheckOutEvent.GetListUserDetail(token))
    }

    var initialSelectedAddress = LocalSelectedIndex.current;
    val selectedAddress = remember {
        mutableStateOf(initialSelectedAddress.intValue)
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = {
                    onNavigateUp()
                },
                leftBtnIcon = R.drawable.ic_left_arrow,
                title = "Địa chỉ nhận hàng",
                onSearch = {})
        },
        bottomBar = {
            FloatingBottomBar(buttonText = "Xác nhận địa chỉ",
                onButtonClick = {
                    initialSelectedAddress.intValue =selectedAddress.value
                    navController.navigateUp()
//                    (Route.CheckOutScreenNavigation.route){
//                        popUpTo(Route.ListInfoScreen.route){
//                            inclusive = true
//                        }
//                    }
                }
            )
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
//                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(state.value.listUserDetail) { index, item ->
                    AddressSelected(
                        isDefault = item.accountDetail.default,
                        isSelected = selectedAddress.value == index,
                        onClick = {
                                  selectedAddress.value = index
                        },
                        name = item.accountDetail.firstName + " " + item.accountDetail.lastName,
                        phoneNumber = formatPhoneNumber(item.accountDetail.phoneNumber),
                        addressNote = item.accountDetail.detailedAddress,
                        address = item.accountDetail.district,
                        onEdit = { /*TODO*/ })
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                }
            }

            FloatingOnBottomBar(buttonText = "+ Thêm địa chỉ mới", onButtonClick = {
                navController.navigate((Route.AddNewAddressScreen.route))
            })
        }
    }
}

@Preview
@Composable
fun ListInfoScreenPreview() {
//    ListInfoScreen(navController = rememberNavController()) {
//
//    }
}