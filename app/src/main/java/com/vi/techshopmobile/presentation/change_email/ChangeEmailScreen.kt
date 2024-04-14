package com.vi.techshopmobile.presentation.change_email

import android.os.CountDownTimer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.data.remote.user.dto.ChangeEmailRequest
import com.vi.techshopmobile.data.remote.user.dto.CheckOtpData
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.authenticate.sign_up.Input
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.forget_password.ForgetPasswordEvent
import com.vi.techshopmobile.presentation.forget_password.ForgetPasswordViewModel
import com.vi.techshopmobile.presentation.forget_password.components.OtpInputField
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.util.convertMilisToMinus
import kotlinx.coroutines.delay


@Composable
fun ChangeEmailScreen(onNavigateUp: () -> Unit, navController: NavController) {
    val viewModel: ChangeEmailViewModel = hiltViewModel()
    val token = LocalToken.current
    val oldEmailState = remember { mutableStateOf("") }
    val newEmailState = remember { mutableStateOf("") }
    val isSendMailState = viewModel.isSendEmail.collectAsState()
    val isSendMailLoading = viewModel.isSendEmailLoading.collectAsState()
    val time: Long = 20000
    val resendTime: Long = 5000
    var otpValue by remember { mutableStateOf("") }
    var resendOtp by remember { mutableStateOf(time) }
    var isResendOtp by remember { mutableStateOf(false) }
    var isOtpSent by remember { mutableStateOf(false) }
    var showResendButton by remember { mutableStateOf(false) }
    var showLoading by remember { mutableStateOf(false) }
    var isButtonClicked by remember { mutableStateOf(false) }
    var isInputDirty by remember { mutableStateOf(false) }





    val resendTimer = object : CountDownTimer(time, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            resendOtp = millisUntilFinished
        }

        override fun onFinish() {
            isResendOtp = true
            resendOtp = time
        }
    }

    LaunchedEffect(showLoading) {
        if (showLoading) {
            delay(resendTime)
            isResendOtp = true
            showResendButton = true
            showLoading = false
        }
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Đổi Email", leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        },
        bottomBar = {
            FloatingBottomBar(
                buttonText = "Thay đổi",
                onButtonClick = {
                    isButtonClicked = true
                    if (oldEmailState.value.isBlank() || newEmailState.value.isBlank() || otpValue.isBlank()) {
                        return@FloatingBottomBar
                    }
                    (viewModel::onEvent)(
                        ChangeEmailEvent.GetAllEventChangeEmail(
                            token = token,
                            changeEmailRequest = ChangeEmailRequest(
                                oldEmail = oldEmailState.value,
                                email = newEmailState.value,
                                verificationCode = otpValue
                            )
                        )
                    )
                    navController.navigate(Route.PersonalInfoScreen.route)
                }
            )



        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .padding(top = topPadding + Dimens.SmallPadding)
                .padding(horizontal = Dimens.SmallPadding),
        ) {
            Text(
                text = ("Cung cấp email mới"),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = Dimens.SmallPadding)
            )
            Input(
                inputText = oldEmailState.value,
                labelText = "Email cũ",
                onChange = {
                    oldEmailState.value = it
                    isInputDirty = it.isNotEmpty()
                },
                modifier = Modifier.fillMaxWidth(),
                errorMessage = if (isInputDirty && oldEmailState.value.isEmpty()) "Email cũ không được để trống" else null            )
            Spacer(modifier = Modifier.height(Dimens.SmallGap))


            if (showLoading) {
                Button(
                    onClick = { /*TODO*/ },
                    enabled = !isSendMailLoading.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(size = 6.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(20.dp)
                            .align(Alignment.CenterVertically),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            } else if (showResendButton) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CustomButton(
                        text = "Nhận lại mã (${resendOtp / 1000}s)",
                        modifier = Modifier.fillMaxWidth(),
                        enable = isResendOtp,
                        onClick = {
                            if (isResendOtp) {
                                (viewModel::onEvent)(
                                    ChangeEmailEvent.SendOtpByMail(
                                        SendOtpByMailData(oldEmailState.value)
                                    )
                                )
                                isResendOtp = false
                                resendTimer.start()
                            }
                        }
                    )

                }
            } else {
                CustomButton(text = "Nhận mã xác nhận", modifier = Modifier.fillMaxWidth()) {
                    (viewModel::onEvent)(
                        ChangeEmailEvent.SendOtpByMail(
                            SendOtpByMailData(oldEmailState.value)
                        )
                    )
                    isOtpSent = true
                    showResendButton = true
                    showLoading = true
                    resendTimer.start()
                }
            }

            Spacer(modifier = Modifier.height(Dimens.SmallGap))

            Input(
                inputText = newEmailState.value,
                labelText = "Email mới",
                onChange = {
                    newEmailState.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                errorMessage = if (newEmailState.value.isBlank()) "Email mới không được để trống" else null

            )

            Spacer(modifier = Modifier.height(Dimens.SmallGap))

            Text(
                text = ("Nhập mã OTP"),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = Dimens.SmallPadding)
            )
            OtpInputField(otpLength = 6, onOtpChanged = { otp ->
                otpValue = otp
            })

            Spacer(modifier = Modifier.weight(1f))

        }
    }
}
