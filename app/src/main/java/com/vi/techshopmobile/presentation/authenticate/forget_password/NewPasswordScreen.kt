package com.vi.techshopmobile.presentation.authenticate.forget_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordOtpData
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.authenticate.sign_up.Input
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.common.TransformableInput
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.mail.MailEvent
import com.vi.techshopmobile.presentation.mail.MailViewModel
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.user.UserEvent
import com.vi.techshopmobile.presentation.user.UserViewModel
import com.vi.techshopmobile.ui.theme.Blue_100
import kotlinx.coroutines.delay

@Composable
fun EnterNewPasswordScreen(
    email: String?,
    verificationCode: String?,
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: UserViewModel = hiltViewModel()
    val isUpdatePasswordState = viewModel.isConfirmDataUpdate.collectAsState()

    val passwordState = remember { mutableStateOf(Input()) }
    val reEnterPasswordState = remember { mutableStateOf(Input()) }


    LaunchedEffect(key1 = isUpdatePasswordState.value) {
        if (isUpdatePasswordState.value) {
            navController.navigate(Route.SignInScreen.route) {
                popUpTo(Route.ForgetPasswordScreenNewPasswordScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = reEnterPasswordState.value.value) {
        delay(500L)
        reEnterPasswordState.value = reEnterPasswordState.value.copy(
            error = if (reEnterPasswordState.value.value != passwordState.value.value) "Mật khẩu không trùng khớp" else null
        )
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                onLeftBtnClick = { onNavigateUp() },
                title = "Xác nhận mật khẩu mới",
                onSearch = {})
        }
    ) {
        val topPadding = it.calculateTopPadding();
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue_100)
                .padding(top = topPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_computer),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(
                        RoundedCornerShape(
                            topStart = Dimens.RadiusSmall,
                            topEnd = Dimens.RadiusSmall
                        )
                    )
                    .background(Color.White)
                    .padding(horizontal = Dimens.SmallPadding)
                    .padding(top = Dimens.MediumPadding2, bottom = Dimens.SmallPadding),
            ) {
                Text(
                    text = "Cung cấp mật khẩu mới",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                )
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                TransformableInput(
                    inputText = passwordState.value.value,
                    errorMessage = passwordState.value.error,
                    labelText = "Mật khẩu",
                    placeHolderText = "********",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    passwordState.value = passwordState.value.copy(
                        value = it,
                        error = if (!("""^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$""".toRegex()
                                .matches(it))
                        ) "Mật khẩu không hợp lệ" else null
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                TransformableInput(
                    inputText = reEnterPasswordState.value.value,
                    errorMessage = reEnterPasswordState.value.error,
                    labelText = "Nhập lại mật khẩu",
                    placeHolderText = "********",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    reEnterPasswordState.value = reEnterPasswordState.value.copy(
                        value = it,
                        error = null
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                CustomButton(text = "Thay đổi", modifier = Modifier.fillMaxWidth()) {
                    (viewModel::onEvent)(
                        UserEvent.updatePasswordOtp(
                            email = email.toString(),
                            updatePasswordOtpData = UpdatePasswordOtpData(
                                password = passwordState.value.value,
                                confirmPassword = reEnterPasswordState.value.value,
                                verificationCode = verificationCode.toString()
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewPasswordScreenPreview() {
    val passwordState = remember { mutableStateOf(Input()) }
    val reEnterPasswordState = remember { mutableStateOf(Input()) }
    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                onLeftBtnClick = { },
                title = "Xác nhận mật khẩu mới",
                onSearch = {})
        }
    ) {
        val topPadding = it.calculateTopPadding();
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue_100)
                .padding(top = topPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_computer),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(
                        RoundedCornerShape(
                            topStart = Dimens.RadiusSmall,
                            topEnd = Dimens.RadiusSmall
                        )
                    )
                    .background(Color.White)
                    .padding(horizontal = Dimens.SmallPadding)
                    .padding(top = Dimens.MediumPadding2, bottom = Dimens.SmallPadding),
            ) {
                Text(
                    text = "Cung cấp mật khẩu mới",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                )
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                TransformableInput(
                    inputText = passwordState.value.value,
                    errorMessage = passwordState.value.error,
                    labelText = "Mật khẩu",
                    placeHolderText = "********",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    passwordState.value = passwordState.value.copy(
                        value = it,
                        error = if (!("""^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$""".toRegex()
                                .matches(it))
                        ) "Mật khẩu không hợp lệ" else null
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                TransformableInput(
                    inputText = reEnterPasswordState.value.value,
                    errorMessage = reEnterPasswordState.value.error,
                    labelText = "Nhập lại mật khẩu",
                    placeHolderText = "********",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    reEnterPasswordState.value = reEnterPasswordState.value.copy(
                        value = it,
                        error = null
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                CustomButton(text = "Thay đổi", modifier = Modifier.fillMaxWidth()) {
//                    (viewModel::onEvent)(
//                        MailEvent.SendOtpByMail(
//                            SendOtpByMailData(email)
//                        )
//                    )
                }
            }
        }
    }
}