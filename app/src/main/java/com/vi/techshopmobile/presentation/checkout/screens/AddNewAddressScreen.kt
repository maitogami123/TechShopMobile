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
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
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
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.ui.theme.Blue_50

@Composable
fun AddNewAddressScreen(
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: CheckOutViewModel = hiltViewModel()
    val isCreateUserDetail = viewModel.isCreateUserDetail.collectAsState()
    val state = viewModel.statePerson.collectAsState()
    val createUserDetailError = viewModel.createUserDetailError.collectAsState()
    val token = LocalToken.current

    val firstName by remember {
        mutableStateOf(mutableStateOf(Input()))
    }
    val lastName by remember {
        mutableStateOf(mutableStateOf(Input()))
    }
    val number by remember {
        mutableStateOf(mutableStateOf(Input()))
    }
    val city by remember {
        mutableStateOf(mutableStateOf(Input()))
    }
    val email by remember {
        mutableStateOf(mutableStateOf(Input()))
    }
    val district by remember {
        mutableStateOf(mutableStateOf(Input()))
    }
    val detailedAddress by remember {
        mutableStateOf(mutableStateOf(Input()))
    }

    var isCheckedBox by remember {
        mutableStateOf(false)
    }

    val collectionProvinces = object : TypeToken<List<Provinces?>?>() {}.type
    val jsonProvinces: List<Provinces> = Gson().fromJson(
        LocalProvinces.current,
        collectionProvinces
    )

    LaunchedEffect(key1 = isCreateUserDetail.value) {
        if (isCreateUserDetail.value) {
            navController.navigate(Route.ListInfoScreen.route)
        }
    }
    var personalInfo by remember {
        mutableStateOf<AccountDetail>(
            AccountDetail(
                id = 0,
                city = "",
                detailedAddress = "",
                phoneNumber = "",
                district = "",
                email = "",
                lastName = "",
                firstName = "",
                default = false
            )
        )
    }
    if (state.value.listUserDetail.isNotEmpty()) {
        personalInfo =
            state.value.listUserDetail[LocalSelectedIndex.current.intValue].accountDetail
    }


    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = {
                    onNavigateUp()
                },
                leftBtnIcon = R.drawable.ic_left_arrow,
                title = "Thêm địa chỉ mới",
                onSearch = {})
        },
        bottomBar = {
            FloatingBottomBar(buttonText = "Thêm mới",
                onButtonClick = {
                    var hasError = false;
                    listOf(
                        firstName, lastName, number, city, email, district, detailedAddress
                    ).forEach { state ->
                        if (state.value.error != null) {
                            hasError = true;
                            return@forEach;
                        }
                        if (state.value.value.isBlank()) {
                            state.value =
                                state.value.copy(error = "Vui lòng nhập/chọn thông tin đầy đủ")
                            hasError = true;
                        } else {
                            state.value = state.value.copy(error = null)
                        }
                    }
                    if (!hasError) {
                        (viewModel::onEvent)(
                            CheckOutEvent.CreateUserDetail(
                                token = token,
                                userDetailRequest = UserDetailRequest(
                                    firstName = firstName.value.value,
                                    lastName = lastName.value.value,
                                    email = email.value.value,
                                    detailedAddress = detailedAddress.value.value,
                                    district = district.value.value,
                                    phoneNumber = number.value.value,
                                    city = city.value.value
                                )
                            )
                        )

                        if (isCheckedBox && state.value.listUserDetail.isNotEmpty()) {
                            (viewModel::onEvent)(
                                CheckOutEvent.UpdateAllUserDetailsToNotDefault(
                                    id = personalInfo.id.toString(),
                                    token = token
                                )
                            )
                        }
                    }
                }
            )
        }
    )
    {
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
                        inputText = lastName.value.value,
                        errorMessage = lastName.value.error,
                        placeHolderText = "Họ",
                        modifier = Modifier.fillMaxWidth(.5f)
                    ) {
                        lastName.value = lastName.value.copy(
                            value = it,
                            error = if ("^(?!\\s*\$).+".toRegex()
                                    .matches(it)
                            ) null else "Vui lòng điền thông tin"
                        )
                    }
                    Spacer(modifier = Modifier.width(Dimens.SmallGap))
                    InputInfo(
                        inputText = firstName.value.value,
                        errorMessage = firstName.value.error,
                        placeHolderText = "Tên",
                    ) {
                        firstName.value = firstName.value.copy(
                            value = it,
                            error = if ("^(?!\\s*\$).+".toRegex()
                                    .matches(it)
                            ) null else "Vui lòng điền thông tin"
                        )
                    }
                }
                InputInfo(
                    inputText = email.value.value,
                    errorMessage = email.value.error,
                    placeHolderText = "Email nhận thông tin đơn hàng",
                ) {
                    email.value = email.value.copy(
                        value = it,
                        error =
                        //if ("^(?!\\s*\$).+".toRegex()
                        if ("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$".toRegex()
                                .matches(it)
                        ) null else
                            if ("^(?!\\s*\$).+".toRegex()
                                    .matches(it)
                            ) "Vui lòng điền chính xác thông tin mail" else "Vui lòng điền thông tin"
                    )
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
                        ) null else if ("^(?!\\s*\$).+".toRegex()
                                .matches(it)
                        ) "Vui lòng điền chính xác số điện thoại" else "Vui lòng điền thông tin"
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
                    mSelectedText = city.value.value,
                    errorMessage = city.value.error,
                    provinces = jsonProvinces
                ) {
                    city.value = city.value.copy(
                        value = it,
                        error = if ("^(?!\\s*\$).+".toRegex()
                                .matches(it)
                        ) null else "Vui lòng chọn tỉnh/thành"
                    )
                    district.value.value
                }
                ComboBoxDistrict(
                    placeHolderText = "Quận/Huyện",
                    provinces = jsonProvinces,
                    citySelected = city.value.value,
                    mSelectedText = district.value.value,
                    errorMessage = district.value.error,
                ) {
                    district.value = district.value.copy(
                        value = it,
                        error = if ("^(?!\\s*\$).+".toRegex()
                                .matches(it)
                        ) null else "Vui lòng chọn quận/huyện"
                    )
                }
                InputInfoNoUnder(
                    inputText = detailedAddress.value.value,
                    errorMessage = detailedAddress.value.error,
                    placeHolderText = "Số nhà, tên đường",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    detailedAddress.value = detailedAddress.value.copy(
                        value = it,
                        error = if ("^(?!\\s*\$).+".toRegex()
                                .matches(it)
                        ) null else "Vui điền địa chỉ chi tiết (số nhà, đường)"
                    )
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

data class Input(
    var value: String = "",
    val error: String? = null
)

@Preview
@Composable
fun AddNewAddressScreenScreen() {
    AddNewAddressScreen(navController = rememberNavController()) {

    }

}