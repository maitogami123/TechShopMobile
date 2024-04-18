package com.vi.techshopmobile.presentation.personal_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.userDetails.dto.UpdateUserDetailRequest
import com.vi.techshopmobile.presentation.Dimens.SmallGap
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.common.InputWithLink
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route


@Composable
fun PersonalInfoScreen(onNavigateUp: () -> Unit, navController: NavController) {
    val viewModel: PersonalInfoViewModel = hiltViewModel()
    val token = LocalToken.current
    val state = viewModel.state.collectAsState()
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
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

    var editable by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(PersonalInfoEvent.GetAllEventPersonalInfo(token))
    }

    LaunchedEffect(key1 = state.value.userDetail.accountDetail) {
        if (state.value.userDetail.accountDetail != null) {
            firstName = state.value.userDetail.accountDetail.firstName
            lastName = state.value.userDetail.accountDetail.lastName
            number.value = Input().copy(value = state.value.userDetail.accountDetail.phoneNumber)
            city = state.value.userDetail.accountDetail!!.city
            email = state.value.userDetail.accountDetail!!.email
            district = state.value.userDetail.accountDetail!!.district
            detailedAddress = state.value.userDetail.accountDetail!!.detailedAddress
        }
    }

    LaunchedEffect(key1 = editable) {
        if (state.value.userDetail.accountDetail != null && editable == true) {
            firstName = state.value.userDetail.accountDetail.firstName
            lastName = state.value.userDetail.accountDetail.lastName
            number.value = Input().copy(value = state.value.userDetail.accountDetail.phoneNumber)
            city = state.value.userDetail.accountDetail!!.city
            email = state.value.userDetail.accountDetail!!.email
            district = state.value.userDetail.accountDetail!!.district
            detailedAddress = state.value.userDetail.accountDetail!!.detailedAddress
        }
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Thông tin cá nhân", leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        }
    ) {
        if (state.value.isLoading) {
            LoadingDialog(isLoading = state.value.isLoading)
        } else {
            if (state.value.userDetail.accountDetail != null) {
                val user = state.value.userDetail.accountDetail

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it),
//                .padding(horizontal = Dimens.SmallPadding),
                    verticalArrangement = Arrangement.spacedBy(SmallPadding)
                ) {}
                val topPadding = it.calculateTopPadding()
                Column(
                    modifier = Modifier
                        .padding(top = topPadding + SmallPadding)
                        .padding(horizontal = SmallPadding),
                    verticalArrangement = Arrangement.spacedBy(SmallPadding)
                ) {
                    Input(
                        enabled = editable,
                        inputText = firstName,
                        labelText = "Họ và tên lót",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        firstName = it
                    }
                    Input(
                        enabled = editable,
                        inputText = lastName,
                        labelText = "Tên",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        lastName = it
                    }
                    state.value.userDetail?.let { accountDetail ->
                        InputWithLink(
                            enabled = false,
                            labelText = "Email",
                            linkLabel = "Đổi gmail",
                            inputText = accountDetail.email,
                            onChange = {},
                        ) {
                            navController.navigate(Route.ChangeEmailScreen.route)
                        }
                    }

                    CustomButton(modifier = Modifier.fillMaxWidth(), text = "Đổi mật khẩu") {
                        navController.navigate(Route.ChangePasswordScreen.route)

                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = SmallPadding)
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(SmallGap)) {
                        Text(
                            text = ("Địa chỉ mặc định hiện tại"),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        state.value.userDetail?.accountDetail?.let { user ->
                            Address(
                                name = user.firstName + " " + user.lastName,
                                phoneNumber = "(+84) " + user.phoneNumber,
                                addressNote = user.detailedAddress,
                                address = user.district + " " + user.city
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (editable == false) "Chỉnh sửa" else "Cập nhật"
                    ) {
                        if (editable == false) {
                            editable = true
                        } else {
                            if (state.value.userDetail.accountDetail != null) {
                                val detail = state.value.userDetail.accountDetail!!;
                                (viewModel::onEvent)(
                                    PersonalInfoEvent.UpdateUserDetail(
                                        token = token,
                                        id = detail.id.toString(),
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
                                editable = false
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Input(
    var value: String = "",
    val error: String? = null
)