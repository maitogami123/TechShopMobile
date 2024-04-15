package com.vi.techshopmobile.presentation.change_email

import android.annotation.SuppressLint
import android.os.CountDownTimer
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.data.remote.user.dto.ChangeEmailRequest
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.forget_password.components.OtpInputField
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.util.convertMilisToMinus


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
fun ChangeEmailScreen(onNavigateUp: () -> Unit, navController: NavController) {
    val viewModel: ChangeEmailViewModel = hiltViewModel()
    val token = LocalToken.current
    val oldEmailState = remember { mutableStateOf(Input()) }
    val newEmailState = remember { mutableStateOf(Input()) }

    val isSendMailLoading = viewModel.isSendEmailLoading.collectAsState()
    val time: Long = 20000
    val otpValue = remember { mutableStateOf(Input()) }
    var resendOtp by remember { mutableStateOf(time) }
    var isResendOtp by remember { mutableStateOf(true) }
    var showResendButton by remember { mutableStateOf(false) }

    val isEnableButton = derivedStateOf { otpValue.value.value.length == 6 }

    val resendTimer = object : CountDownTimer(time, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            resendOtp = millisUntilFinished
        }

        override fun onFinish() {
            isResendOtp = true
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
                enable = isEnableButton.value,
                buttonText = "Thay đổi",
                onButtonClick = {
                    var hasError = false;
                    listOf(
                        oldEmailState, newEmailState, otpValue
                    ).forEach { state ->
                        if (state.value.error != null) {
                            hasError = true;
                            return@forEach;
                        }
                        if (state.value.value.isBlank()) {
                            state.value =
                                state.value.copy(error = "Vui lòng nhập thông tin đầy đủ")
                            hasError = true;
                        } else {
                            state.value = state.value.copy(error = null)
                        }
                    }
                    if (!hasError) {
                        (viewModel::onEvent)(
                            ChangeEmailEvent.GetAllEventChangeEmail(
                                token = token,
                                changeEmailRequest = ChangeEmailRequest(
                                    oldEmail = oldEmailState.value.value,
                                    email = newEmailState.value.value,
                                    verificationCode = otpValue.value.value
                                )
                            )
                        )
                        navController.navigate(Route.PersonalInfoScreen.route)
                    }
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
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight(600)
                ),
                modifier = Modifier.padding(bottom = Dimens.SmallPadding)
            )
            Input(
                inputText = oldEmailState.value.value,
                errorMessage = oldEmailState.value.error,
                labelText = "Nhập mail cũ",
                onChange = {
                    oldEmailState.value = oldEmailState.value.copy(
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
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(Dimens.SmallGap))


            if (isSendMailLoading.value) {
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
            } else {
                if (showResendButton) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CustomButton(
                            text = if (isResendOtp) "Nhận lại mã " else "Nhận lại mã " + "(" + convertMilisToMinus(
                                resendOtp
                            ) + ")",
                            modifier = Modifier.fillMaxWidth(),
                            enable = isResendOtp,
                            onClick = {
                                if (isResendOtp) {
                                    (viewModel::onEvent)(
                                        ChangeEmailEvent.SendOtpByMail(
                                            SendOtpByMailData(oldEmailState.value.value)
                                        )
                                    )
                                    resendTimer.start()
                                }
                                isResendOtp = !isResendOtp
                            }
                        )
                    }
                } else {
                    CustomButton(text = "Nhận mã xác nhận", modifier = Modifier.fillMaxWidth()) {
                        var hasError = false;
                        if (oldEmailState.value.error != null) {
                            hasError = true;
                        }
                        if (oldEmailState.value.value.isBlank()) {
                            oldEmailState.value =
                                oldEmailState.value.copy(error = "Vui lòng nhập email cũ để nhận mã OTP")
                            hasError = true;
                        } else {
                            oldEmailState.value = oldEmailState.value.copy(error = null)
                        }
                        if (!hasError) {
                            (viewModel::onEvent)(
                                ChangeEmailEvent.SendOtpByMail(
                                    SendOtpByMailData(oldEmailState.value.value)
                                )
                            )
                            showResendButton = true
                            resendTimer.start()
                            isResendOtp = false
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.SmallGap))

            Input(
                inputText = newEmailState.value.value,
                labelText = "Nhập mail mới",
                onChange = {
                    newEmailState.value = newEmailState.value.copy(
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
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(Dimens.SmallGap))

            Text(
                text = ("Nhập mã OTP"),
                style = MaterialTheme.typography.labelLarge,
            )
            OtpInputField(otpLength = 6, onOtpChanged = { otp ->
                otpValue.value = otpValue.value.copy(
                    value = otp
                )
            })
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

data class Input(
    val value: String = "",
    val error: String? = null
)