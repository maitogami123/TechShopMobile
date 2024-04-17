package com.vi.techshopmobile.presentation.checkout.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vi.techshopmobile.LocalProvinces
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.userDetails.dto.UpdateUserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.AccountDetail
import com.vi.techshopmobile.domain.model.Provinces
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.checkout.CheckOutEvent
import com.vi.techshopmobile.presentation.checkout.CheckOutViewModel
import com.vi.techshopmobile.presentation.checkout.LocalSelectedIndex
import com.vi.techshopmobile.presentation.checkout.components.CheckBoxText
import com.vi.techshopmobile.presentation.checkout.components.ComboBox
import com.vi.techshopmobile.presentation.checkout.components.ComboBoxDistrict
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.InputInfo
import com.vi.techshopmobile.presentation.common.InputInfoNoUnder
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.product_details.ProductDetailsEvent
import com.vi.techshopmobile.ui.theme.Blue_50

@Composable
fun DetailAddressScreen(
    navController: NavController,
    id: String,
    onNavigateUp: () -> Unit
) {
    val viewModel: CheckOutViewModel = hiltViewModel()
    val isUpdateUserDetail = viewModel.isUpdateUserDetail.collectAsState()
    val state = viewModel.statePerson.collectAsState()
    val loading = viewModel.isLoading.collectAsState()
    val createUserDetailError = viewModel.createUserDetailError.collectAsState()
    val token = LocalToken.current

    LaunchedEffect(key1 = id) {
        (viewModel::onEvent)(CheckOutEvent.GetUserDetailById(token, id))
    }

    LaunchedEffect(key1 = isUpdateUserDetail.value) {
        if (isUpdateUserDetail.value) {
            navController.navigate(Route.UserSettingScreen.route)
        }
    }

    var firstName by remember {
        mutableStateOf(

            ""
        )
    }
    var lastName by remember {
        mutableStateOf(
            ""
        )
    }
    val number by remember {
        mutableStateOf(
            mutableStateOf(
                Input()
            )
        )
    }
    var city by remember {
        mutableStateOf(
            ""
        )
    }
    var email by remember {
        mutableStateOf(
            ""
        )
    }
    var district by remember {
        mutableStateOf(
            ""
        )
    }
    var detailedAddress by remember {
        mutableStateOf(
            ""
        )
    }
    var isCheckedBox by remember {
        mutableStateOf(
            false
        )
    }

    val collectionProvinces = object : TypeToken<List<Provinces?>?>() {}.type
    val jsonProvinces: List<Provinces> = Gson().fromJson(
        LocalProvinces.current,
        collectionProvinces
    )

    LaunchedEffect(key1 = state.value.detailUserDetail) {
        if (state.value.detailUserDetail != null) {
            firstName = state.value.detailUserDetail!!.firstName
            lastName = state.value.detailUserDetail!!.lastName
            number.value = Input().copy(value = state.value.detailUserDetail!!.phoneNumber)
            city = state.value.detailUserDetail!!.city
            email = state.value.detailUserDetail!!.email
            district = state.value.detailUserDetail!!.district
            detailedAddress = state.value.detailUserDetail!!.detailedAddress
            isCheckedBox = state.value.detailUserDetail!!.default
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
                title = "Cập nhật địa chỉ",
                onSearch = {})
        },
        bottomBar = {
            if (!state.value.isLoading) {
                FloatingBottomBar(buttonText = "Cập nhật",
                    onButtonClick = {
                        if (state.value.detailUserDetail != null) {
                            val detail = state.value.detailUserDetail!!;
                            (viewModel::onEvent)(
                                CheckOutEvent.UpdateUserDetail(
                                    token = token,
                                    id = id,
                                    updateUserDetailRequest = UpdateUserDetailRequest(
                                        firstName = if (firstName == detail.firstName || firstName.isEmpty()) detail.firstName else firstName,
                                        lastName = if (lastName == detail.lastName || lastName.isEmpty()) detail.lastName else lastName,
                                        email = if (email == detail.email || email.isEmpty()) detail.email else email,
                                        detailedAddress = if (detailedAddress == detail.detailedAddress || detailedAddress.isEmpty()) detail.detailedAddress else detailedAddress,
                                        district = if (district == detail.district || district.isEmpty()) detail.district else district,
                                        phoneNumber = if (number.value.value == detail.phoneNumber || number.value.value.isEmpty()) detail.phoneNumber else number.value.value,
                                        city = if (city == detail.city || city.isEmpty()) detail.city else city
                                    )
                                )
                            )
                            if (isCheckedBox == true) {
                                (viewModel::onEvent)(
                                    CheckOutEvent.UpdateAllUserDetailsToNotDefault(
                                        id = id,
                                        token = token
                                    )
                                )
                            }
                        }
                    }

                )
            }
        }
    )
    {
        if (state.value.isLoading) {
            LoadingDialog(isLoading = state.value.isLoading)
        } else {
            if (state.value.detailUserDetail != null) {
                val detail = state.value.detailUserDetail!!
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    item {
                        Text(
                            modifier = Modifier
                                .background(Blue_50)
                                .padding(vertical = Dimens.SmallGap)
                                .fillMaxWidth()
                                .padding(start = Dimens.SmallGap),
                            text = "Liên hệ"
                        )
                        Row {
                            InputInfo(
                                inputText = lastName,
                                placeHolderText = "Họ",
                                modifier = Modifier.fillMaxWidth(.5f)
                            ) {
                                lastName = it
                            }
                            Spacer(modifier = Modifier.width(Dimens.SmallGap))
                            InputInfo(
                                inputText = firstName,
                                placeHolderText = "Tên",
                            ) {
                                firstName = it
                            }
                        }
                        InputInfo(
                            inputText = email,
                            placeHolderText = "Email nhận thông tin đơn hàng",
                        ) {
                            email = it
                        }
                        InputInfoNoUnder(
                            inputText = number.value.value,
                            errorMessage = number.value.error,
                            placeHolderText = "Số điện thoại",
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Number
                        ) {
                            number.value = number.value.copy(
                                value = it,
                                error = if ("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$".toRegex()
                                        .matches(it)
                                ) null else
                                    "Vui lòng điền chính xác số điện thoại"
                            )
                        }
                        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                        Text(
                            modifier = Modifier
                                .background(Blue_50)
                                .padding(vertical = Dimens.SmallGap)
                                .fillMaxWidth()
                                .padding(start = Dimens.SmallGap),
                            text = "Địa chỉ"
                        )
                        ComboBox(
                            placeHolderText = "Tỉnh",
                            mSelectedText = city,
                            provinces = jsonProvinces
                        ) {
                            city = it
                        }
                        ComboBoxDistrict(
                            placeHolderText = "Quận/Huyện",
                            provinces = jsonProvinces,
                            citySelected = city,
                            mSelectedText = district,
                        ) {
                            district = it
                        }
                        InputInfoNoUnder(
                            inputText = detailedAddress,
                            placeHolderText = "Số nhà, tên đường",
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            detailedAddress = it
                        }

                        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                        Text(
                            modifier = Modifier
                                .background(Blue_50)
                                .padding(vertical = Dimens.SmallGap)
                                .fillMaxWidth()
                                .padding(start = Dimens.SmallGap),
                            text = "Cài đặt"
                        )

                        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
                        CheckBoxText(
                            title = "Đặt làm địa chỉ mặc định",
                            isChecked = isCheckedBox
                        ) {
                            isCheckedBox = !isCheckedBox
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailAddressScreenScreen() {
    DetailAddressScreen(navController = rememberNavController(), id = "1") {

    }

}