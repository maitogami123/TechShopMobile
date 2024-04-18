package com.vi.techshopmobile.presentation.personal_address

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.checkout.navigateToDetailAddress
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel
import com.vi.techshopmobile.util.formatPhoneNumber

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun PersonalAddressScreen(onNavigateUp: () -> Unit, navController: NavController) {
    val viewModel: PersonalInfoViewModel = hiltViewModel()
    val token = LocalToken.current
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(PersonalInfoEvent.GetListUserDetail(token))
    }
    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Địa chỉ nhận hàng", leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        },
        bottomBar = {
            if (!state.value.isLoading) {
                FloatingBottomBar(
                    buttonText = "+ Thêm địa chỉ mới",
                    onButtonClick = {
                        navController.navigate(Route.AddNewAddressScreen.route)
                    }
                )
            }
        }
    ) {
        if (state.value.isLoading) {
            LoadingDialog(isLoading = state.value.isLoading)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
                .padding(horizontal = Dimens.SmallPadding),
                verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding)
            ) {
                itemsIndexed(state.value.listUserDetail) { index, item ->
                    Address(
                        name = item.accountDetail.lastName + " " + item.accountDetail.firstName,
                        phoneNumber = formatPhoneNumber(item.accountDetail.phoneNumber),
                        addressNote = item.accountDetail.detailedAddress,
                        address = item.accountDetail.district + ", " + item.accountDetail.city,
                        isDefault = item.accountDetail.default,
                        onEdit = {
                            navigateToDetailAddress(
                                navController,
                                item.accountDetail.id.toString()
                            )
                        }
                    )
                    Divider()
                }
            }
        }
    }
}


@Preview
@Composable
fun PersonalAddressScreenPreview() {
    PersonalAddressScreen(onNavigateUp = { /*TODO*/ }, navController = rememberNavController())
}