package com.vi.techshopmobile.presentation.authenticate.forget_password

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.mail.MailEvent
import com.vi.techshopmobile.presentation.mail.MailViewModel
import com.vi.techshopmobile.ui.theme.Blue_100

@Composable
fun EnterEmailScreen(
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val viewModel: MailViewModel = hiltViewModel()
    val isSendMailState = viewModel.isSendEmail.collectAsState()

    //val gmail = viewModel.email.collectAsState()
    var email by remember {
        mutableStateOf("")
    }

    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isSendMailState.value) {
        if (isSendMailState.value) {
            navController.navigate(Route.ForgetPasswordScreenOTPScreen.route + "/$email")
        }
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                onLeftBtnClick = { onNavigateUp() },
                title = "Enter email",
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
                    text = "Nhập Gmail nhận mã",
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                Input(
                    //errorMessage = "usernameState.value.error",
                    labelText = "Hãy nhập đúng gmail mà bạn đã đắng ký tài khoản.",
                    inputText = email,
                    placeHolderText = "Gmail",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    email = it
                }
                Spacer(modifier = Modifier.height(110.dp))
                if (loading) {
                    Button(onClick = { /*TODO*/ }, enabled = false,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ), shape = RoundedCornerShape(size = 6.dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(20.dp).align(Alignment.CenterVertically),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                } else {
                    CustomButton(text = "Nhập mã xác nhận", modifier = Modifier.fillMaxWidth()) {
                        loading = true;
                        (viewModel::onEvent)(
                            MailEvent.SendOtpByMail(
                                SendOtpByMailData(email)
                            )
                        )
                    }
                }
            }
        }
    }
}

data class Input(
    val value: String = "",
    val error: String? = null
)


@Composable
@Preview
fun EnterEmailScreenPreview() {
    var gmail by remember {
        mutableStateOf("")
    }
    var loading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                onLeftBtnClick = { },
                title = "Enter email",
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
                    text = "Nhập Gmail nhận mã",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                )
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                Input(
                    //errorMessage = "usernameState.value.error",
                    labelText = "Hãy nhập đúng gmail mà bạn đã đắng ký tài khoản.",
                    inputText = gmail,
                    placeHolderText = "Gmail",
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    gmail = it
                }
                Spacer(modifier = Modifier.height(110.dp))

                if (loading) {
                    Button(onClick = { /*TODO*/ }, enabled = false,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ), shape = RoundedCornerShape(size = 6.dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(20.dp).align(Alignment.CenterVertically),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                } else {
                    CustomButton(text = "Đăng nhập",
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        loading = true
                    }
                }


//                    (viewModel::onEvent)(
//                        AuthenticateEvent.LoginEvent(
//                            SignInData(
//                                username,
//                                password
//                            )
//                        )
//                    )

            }
        }
    }
}