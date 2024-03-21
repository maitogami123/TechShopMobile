package com.vi.techshopmobile.presentation.user.forget_password

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.user.forget_password.components.OtpInputField
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.mail.MailEvent
import com.vi.techshopmobile.presentation.mail.MailViewModel
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.user.UserEvent
import com.vi.techshopmobile.presentation.user.UserViewModel
import com.vi.techshopmobile.ui.theme.Blue_100
import com.vi.techshopmobile.util.connectInputtedCode
import com.vi.techshopmobile.util.convertMilisToMinus


@SuppressLint("UnrememberedMutableState")
@Composable
fun EnterOTPScreen(
    email: String?,
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: UserViewModel = hiltViewModel()
    val viewMailModel: MailViewModel = hiltViewModel()
    val isConfirmOtpState = viewModel.isConfirmOtp.collectAsState()
    val time: Long = 10000
    var otpValue by remember {
        mutableStateOf("")
    }
    val isEnableButton = derivedStateOf { otpValue.length == 6 }
    var resendOtp by remember {
        mutableStateOf(time)
    }

    var isResendOtp by remember { mutableStateOf(false) }


    val timer = object : CountDownTimer(time, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            resendOtp = millisUntilFinished
        }

        override fun onFinish() {
            isResendOtp = true
        }
    }

    LaunchedEffect(key1 = true) {
        timer.start()
    }

    LaunchedEffect(key1 = isConfirmOtpState.value) {
        if (isConfirmOtpState.value) {
            navController.navigate(
                Route.ForgetPasswordScreenNewPasswordScreen.route +
                        "/$email" + "/" +
                        otpValue
            )
        }
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                onLeftBtnClick = { onNavigateUp() },
                title = "Enter OTP",
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
                    text = "Nhập mã xác nhận",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    //wrapContentWidth(align = Alignment.CenterHorizontally, unbounded = true),
                    text = "Mã xác nhận được gửi qua gmail $email",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                    ),
                )

                //RowOtpCode(textList, requesterList)
                OtpInputField(otpLength = 6, onOtpChanged = { otp ->
                    otpValue = otp
                })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = isResendOtp) {
                            isResendOtp = !isResendOtp
                            (viewMailModel::onEvent)(
                                MailEvent.SendOtpByMail(
                                    SendOtpByMailData(email.toString())
                                )
                            )
                            timer.start()
                        }
                        .alpha(if (isResendOtp) 1f else 0.2f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Gửi lại mã",
                        style = MaterialTheme.typography.titleSmall,
                        textDecoration = TextDecoration.Underline,
                        color = if (!isResendOtp) Color(0xff8A8A8A) else Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if (!isResendOtp) {
                        Text(
                            text = convertMilisToMinus(resendOtp),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(.5f))
                CustomButton(text = "Xác nhận", modifier = Modifier.fillMaxWidth(), enable = isEnableButton.value) {
                    (viewModel::onEvent)(
                        UserEvent.checkOtp(
                            email = email.toString(),
                            verificationCode = otpValue
                        )
                    )
                }
            }

        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun EnterOTPScreenPreview() {
    var resendOtp by remember {
        mutableStateOf(10000.toLong())
    }

    var otpValue by remember {
        mutableStateOf("")
    }

    val isEnableButton = derivedStateOf { otpValue.length == 6 }

    var isResendOtp by remember { mutableStateOf(false) }


    val timer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            resendOtp = millisUntilFinished
        }

        override fun onFinish() {
            isResendOtp = true
        }
    }

    LaunchedEffect(key1 = true) {
        timer.start()
    }
    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                onLeftBtnClick = { },
                title = "Enter OTP",
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
                    text = "Nhập mã xác nhận",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    //wrapContentWidth(align = Alignment.CenterHorizontally, unbounded = true),
                    text = "Mã xác nhận được gửi qua gmail quocvi@gmail.com",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                    ),
                )

                //RowOtpCode(textList, requesterList)
                OtpInputField(otpLength = 6, onOtpChanged = { otp ->
                    otpValue = otp
                })

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = isResendOtp) {
                            isResendOtp = !isResendOtp
//                            (viewMailModel::onEvent)(
//                                MailEvent.SendOtpByMail(
//                                    SendOtpByMailData("email.toString()")
//                                )
//                            )
                            timer.start()
                        }
                        .alpha(if (isResendOtp) 1f else 0.4f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Gửi lại mã",
                        style = MaterialTheme.typography.titleSmall,
                        textDecoration = TextDecoration.Underline,
                        color = if (!isResendOtp) Color(0xff8A8A8A) else Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if (!isResendOtp) {
                        Text(
                            text = convertMilisToMinus(resendOtp),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(.5f))
                CustomButton(text = "Xác nhận", modifier = Modifier.fillMaxWidth(), enable = isEnableButton.value) {
//                    (viewModel::onEvent)(
//                        UserEvent.SendOtpByMail(
//                            SendOtpByMailData(gmail)
//                        )
//                    )
                }
            }

        }
    }
}