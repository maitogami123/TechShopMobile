package com.vi.techshopmobile.presentation.change_password

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordRequest
import com.vi.techshopmobile.domain.model.UserToken
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.authenticate.sign_up.Input
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.TransformableInput
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(
    onNavigateUp: () -> Unit
) {
    val token = LocalToken.current
    val viewModel: ChangePasswordViewModel = hiltViewModel()
    val oldPasswordState = remember { mutableStateOf(Input()) }
    val newPasswordState = remember { mutableStateOf(Input()) }
    val reEnterPasswordState = remember { mutableStateOf(Input()) }

    LaunchedEffect(key1 = oldPasswordState.value.value) {
        delay(500L)

        val error = if (oldPasswordState.value == newPasswordState.value) {
            null
        } else {
            "Mật khẩu không đúng"
        }
        oldPasswordState.value = oldPasswordState.value.copy(error = error)
    }

    LaunchedEffect(key1 = reEnterPasswordState.value.value) {
        delay(500L)
        reEnterPasswordState.value = reEnterPasswordState.value.copy(
            error = if (reEnterPasswordState.value.value != newPasswordState.value.value) "Mật khẩu không trùng khớp" else null
        )

    }


    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Đổi mật khẩu", leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .padding(top = topPadding + Dimens.SmallPadding)
                .padding(horizontal = Dimens.SmallPadding),
        ) {
            Text(
                text = ("Cung cấp mật khẩu mới"),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = SmallPadding)
            )
            TransformableInput(
                inputText = oldPasswordState.value.value,
                labelText = "Nhập mật khẩu cũ",
//                errorMessage = oldPasswordState.value.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
            ) {
                oldPasswordState.value = oldPasswordState.value.copy(
                    value = it,
                    error = null
                )
            }
            TransformableInput(
                inputText = newPasswordState.value.value,
                labelText = "Nhập mật khẩu mới",
                errorMessage = newPasswordState.value.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
            ) {
                newPasswordState.value = newPasswordState.value.copy(
                    value = it,
                    error = if (!("""^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$""".toRegex()
                            .matches(it))
                    ) "Mật khẩu không hợp lệ" else null
                )
            }
            TransformableInput(
                inputText = reEnterPasswordState.value.value,
                labelText = "Nhập lại mật khẩu mới",
                errorMessage = reEnterPasswordState.value.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
            ) {
                reEnterPasswordState.value = reEnterPasswordState.value.copy(
                    value = it,
                    error = null
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(modifier = Modifier.fillMaxWidth(), text = "Thay đổi") {
                if (oldPasswordState.value.value.isNotEmpty() &&
                    newPasswordState.value.value.isNotEmpty() &&
                    reEnterPasswordState.value.value.isNotEmpty() &&
                    newPasswordState.value.value == reEnterPasswordState.value.value
                ) {
                    (viewModel::onEvent)(
                        ChangePasswordEvent.ChangePassword(
                            token = token,
                            changePassword = ChangePasswordRequest(
                                oldpassword = oldPasswordState.value.value,
                                password = newPasswordState.value.value,
                                confirmPassword = reEnterPasswordState.value.value
                            )
                        )
                    )
                    oldPasswordState.value = Input()
                    newPasswordState.value = Input()
                    reEnterPasswordState.value = Input()
                } else{
                    if (oldPasswordState.value.value.isEmpty()) {
                        oldPasswordState.value = oldPasswordState.value.copy(
                            error = "Vui lòng nhập mật khẩu cũ"
                        )
                    }
                    if (newPasswordState.value.value.isEmpty()) {
                        newPasswordState.value = newPasswordState.value.copy(
                            error = "Vui lòng nhập mật khẩu mới"
                        )
                    }
                    if (reEnterPasswordState.value.value.isEmpty()) {
                        reEnterPasswordState.value = reEnterPasswordState.value.copy(
                            error = "Vui lòng nhập lại mật khẩu mới"
                        )
                    }
                }
            }
        }
    }
}