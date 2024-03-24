package com.vi.techshopmobile.presentation.authenticate.sign_up

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.authenticate.dto.SignUpData
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.Dimens.MediumPadding2
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.authenticate.AuthenticateEvent
import com.vi.techshopmobile.presentation.authenticate.AuthenticateViewModel
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.common.TransformableInput
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.ui.theme.Blue_100
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(navGraphController: NavController, onNavigateUp: () -> Unit) {

    val viewModel: AuthenticateViewModel = hiltViewModel()
    val isLoggedInState = viewModel.isLoggedIn.collectAsState();
    val registerError = viewModel.registerError.collectAsState()

    val usernameState = remember { mutableStateOf(Input()) }
    val emailState = remember { mutableStateOf(Input()) }
    val passwordState = remember { mutableStateOf(Input()) }
    val reEnterPasswordState = remember { mutableStateOf(Input()) }

    //  Check if register is successful or not, if so redirect user to homeScreen
    LaunchedEffect(key1 = isLoggedInState.value) {
        if (isLoggedInState.value) {
            navGraphController.navigate(Route.TechShopNavigation.route) {
                popUpTo(Route.SignInScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    // Check error in backend response
    LaunchedEffect(key1 = registerError.value) {
        if (registerError.value.contains("username")) {
            usernameState.value = usernameState.value.copy(
                error = "Tài khoản đã tồn tại"
            )
        } else if (registerError.value.contains("email")) {
            emailState.value = emailState.value.copy(
                error = "Email đã được sử dụng"
            )
        }
        Log.d("RegisterError", registerError.value)
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
                onLeftBtnClick = { onNavigateUp() },
                leftBtnIcon = R.drawable.ic_left_arrow,
                title = "Đăng kí",
                onSearch = {})
        }
    ) {
        val topPadding = it.calculateTopPadding()
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
                    .clip(
                        RoundedCornerShape(
                            topStart = Dimens.RadiusSmall,
                            topEnd = Dimens.RadiusSmall
                        )
                    )
                    .wrapContentSize()
                    .background(Color.White)
                    .padding(horizontal = SmallPadding)
                    .padding(top = MediumPadding2, bottom = SmallPadding),
            ) {
                Text(
                    text = "Mua sắm cùng TechShop",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                )
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                Input(
                    inputText = usernameState.value.value,
                    errorMessage = usernameState.value.error,
                    labelText = "Tài khoản",
                    placeHolderText = "VD: UsernameA",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    usernameState.value = usernameState.value.copy(
                        value = it,
                        error = null
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                Input(
                    inputText = emailState.value.value,
                    errorMessage = emailState.value.error,
                    labelText = "Email",
                    placeHolderText = "VD: email@domain.com",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    emailState.value = emailState.value.copy(
                        value = it,
                        error = null
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                TransformableInput(
                    inputText = passwordState.value.value,
                    errorMessage = passwordState.value.error,
                    labelText = "Mật khẩu",
                    placeHolderText = "VD: Axl@12345",
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
                    placeHolderText = "Nhập lại mật khẩu",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    reEnterPasswordState.value = reEnterPasswordState.value.copy(
                        value = it,
                        error = null
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.SmallGap))
                CustomButton(text = "Đăng ký", modifier = Modifier.fillMaxWidth()) {
                    var hasError = false;
                    listOf(
                        usernameState,
                        emailState,
                        passwordState,
                        reEnterPasswordState
                    ).forEach { state ->
                        if (state.value.error != null) {
                            hasError = true;
                            return@forEach;
                        }
                        if (state.value.value.isBlank()) {
                            state.value = state.value.copy(error = "Không được để trống")
                            hasError = true;
                        } else {
                            state.value = state.value.copy(error = null)
                        }
                    }
                    if (!hasError) {
                        (viewModel::onEvent)(
                            AuthenticateEvent.RegisterEvent(
                                SignUpData(
                                    username = usernameState.value.value,
                                    password = passwordState.value.value,
                                    email = emailState.value.value,
                                    confirmPassword = reEnterPasswordState.value.value
                                )
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

@Preview
@Composable
private fun PreviewSignUpScreen() {
    TechShopMobileTheme {
        val navController = rememberNavController()
        SignUpScreen(navController) {

        }
    }
}