package com.vi.techshopmobile.presentation.personal_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.SmallGap
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.common.InputWithLink
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route


@Composable
fun PersonalInfoScreen(onNavigateUp: () -> Unit, navController: NavController) {
    val viewModel: PersonalInfoViewModel = hiltViewModel()
    val token = LocalToken.current
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(PersonalInfoEvent.GetAllEventPersonalInfo(token))
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
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .padding(top = topPadding + SmallPadding)
                .padding(horizontal = SmallPadding),
            verticalArrangement = Arrangement.spacedBy(SmallPadding)
        ) {
            state.value.userDetail?.accountDetail?.let { accountDetail ->
                Input(
                    inputText = accountDetail.firstName,
                    labelText = "Họ và tên lót",
                    modifier = Modifier.fillMaxWidth()
                ) {}
            }
            state.value.userDetail?.accountDetail?.let { accountDetail ->
                Input(
                    inputText = accountDetail.lastName,
                    labelText = "Tên",
                    modifier = Modifier.fillMaxWidth()
                ) {}
            }

            state.value.userDetail?.let { accountDetail ->
                InputWithLink(
                    labelText = "Email",
                    linkLabel = "Đổi gmail",
                    inputText = accountDetail.email,
                    onChange = {}) {
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
            CustomButton(modifier = Modifier.fillMaxWidth(), text = "Lưu thay đổi") {}
        }
    }
}

@Preview
@Composable
fun PersonalInfoScreenPreview() {
    PersonalInfoScreen {

    }
}
