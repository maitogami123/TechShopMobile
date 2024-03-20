package com.vi.techshopmobile.presentation.authenticate.forget_password

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.data.remote.user.dto.CheckOtpData
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.authenticate.forget_password.components.RowOtpCode
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private var textList = listOf(
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
)

private val requesterList = listOf(
    FocusRequester(),
    FocusRequester(),
    FocusRequester(),
    FocusRequester(),
    FocusRequester(),
    FocusRequester(),
)


@Composable
fun EnterOTPScreen(
    email: String?,
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: UserViewModel = hiltViewModel()
    val viewMailModel: MailViewModel = hiltViewModel()
    val isConfirmOtpState = viewModel.isConfirmOtp.collectAsState()

    val time:Long = 10000

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
                        "/$email" + "/"+
                        connectInputtedCode(textList)
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

                RowOtpCode(textList, requesterList)

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
                        color = Color(0xff8A8A8A)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if(!isResendOtp) {
                        Text(
                            text = convertMilisToMinus(resendOtp),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
                CustomButton(text = "Xác nhận", modifier = Modifier.fillMaxWidth()) {
                    (viewModel::onEvent)(
                        UserEvent.checkOtp(
                            email = email.toString(),
                            verificationCode = connectInputtedCode(
                                textList
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
fun EnterOTPScreenPreview() {
    var resendOtp by remember {
        mutableStateOf(300000.toLong())
    }

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

                RowOtpCode(textList, requesterList)

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
                        color = Color(0xff8A8A8A)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if(!isResendOtp){
                        Text(
                            text =convertMilisToMinus(resendOtp),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
                CustomButton(text = "Xác nhận", modifier = Modifier.fillMaxWidth()) {
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


//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun ContentView(
//    textList: List<MutableState<TextFieldValue>>,
//    requesterList: List<FocusRequester>,
//    modifier: Modifier = Modifier
//) {
//    val focusManager = LocalFocusManager.current
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val context = LocalContext.current
//
//    Box(modifier = Modifier.width(340.dp)) {
//        Row(
//            modifier = modifier
//                .padding(horizontal = 20.dp)
//                .padding(top = 58.dp)
//                .align(Alignment.TopCenter)
//        ) {
//            for (i in textList.indices) {
//                InputView(
//                    value = textList[i].value,
//                    onValueChange = { newValue ->
//                        //if old value is not empty, just return
//                        if (textList[i].value.text != "") {
//                            if (newValue.text == "") {
//                                //before return, if the new value is empty, set the text field to empty
//                                textList[i].value = TextFieldValue(
//                                    text = "",
//                                    selection = TextRange(0)
//                                )
//                            }
//
//                            return@InputView
//                        }
//
//                        //set new value and move cursor to the end
//                        textList[i].value = TextFieldValue(
//                            text = newValue.text,
//                            selection = TextRange(newValue.text.length)
//                        )
//                        connectInputtedCode(textList) {
//                            focusManager.clearFocus()
//                            keyboardController?.hide()
//                        }
//                        nextFocus(textList, requesterList)
//                    },
//                    focusRequest = requesterList[i]
//                )
//            }
//        }
//    }
//
//
//    LaunchedEffect(key1 = null, block = {
//        delay(300)
//        requesterList[0]
//    })
//}
//
//fun connectInputtedCode(
//    textList: List<MutableState<TextFieldValue>>,
//    onController: (() -> Unit)? = null
//) {
//    var code = ""
//    for (text in textList) {
//        code += text.value.text
//    }
//}
//
//
//fun nextFocus(textList: List<MutableState<TextFieldValue>>, requesterList: List<FocusRequester>) {
//    for (index in textList.indices) {
//        if (textList[index].value.text == "") {
//            if (index < textList.size) {
//                requesterList[index].requestFocus()
//                break
//            }
//        }
//    }
//}

//@Composable
//fun InputView(
//    value: TextFieldValue,
//    onValueChange: (value: TextFieldValue) -> Unit,
//    focusRequest: FocusRequester
//) {
//    BasicTextField(
//        value = value, onValueChange = onValueChange,
//        modifier = Modifier
//            .padding(4.dp)
//            .clip(RoundedCornerShape(4.dp))
//            .background(
//                Color(0xffE1EFFE)
//            )
//            .wrapContentSize()
//            .focusRequester(focusRequest),
//        maxLines = 1,
//        decorationBox = { innerTextField ->
//            Box(
//                modifier = Modifier
//                    .width(40.dp)
//                    .height(48.dp),
//                contentAlignment = Alignment.Center,
//            ) {
//                innerTextField()
//            }
//        },
//        cursorBrush = SolidColor(Color.White),
//        textStyle = MaterialTheme.typography.displayLarge,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Number,
//            imeAction = ImeAction.None
//        ),
//        keyboardActions = KeyboardActions(onDone = null)
//    )
//}