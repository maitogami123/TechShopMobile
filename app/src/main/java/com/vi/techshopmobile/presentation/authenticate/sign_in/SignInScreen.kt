package com.vi.techshopmobile.presentation.authenticate.sign_in

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.presentation.common.TransformableInput
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.ui.theme.Blue_100
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun SignInScreen(
    navGraphController: NavController,
    navController: NavController
) {
    val viewModel: SignInViewModel = hiltViewModel()

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = {
                    navGraphController.navigate(Route.TechShopNavigation.route) {
                        popUpTo(Route.AppStartNavigation.route) {
                            inclusive = true
                        }
                    }
                },
                leftBtnIcon = R.drawable.ic_cross,
                title = "Đăng nhập",
                onSearch = {})
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .padding(top = topPadding)
                .background(Blue_100)
        ) {
            val topPadding = it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Blue_100)
                    .padding(top = topPadding)
                    .padding(horizontal = SmallPadding)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_computer),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = "Đăng nhập")
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "Chưa có tài khoản?")
                        Text(text = "Đăng kí ngay!",
                            modifier = Modifier
                                .clickable { navController.navigate(Route.SignUpScreen.route) })
                    }
                    Input(
                        inputText = username,
                        placeHolderText = "Tài khoản",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        username = it
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    TransformableInput(
                        inputText = password,
                        placeHolderText = "Mật khẩu",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        password = it
                    }
                    Text(
                        "Quên mật khẩu",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Route.ForgetPasswordScreenEmailScreen.route) },
                        textAlign = TextAlign.Right
                    )
                    CustomButton(text = "Đăng nhập", modifier = Modifier.fillMaxWidth()) {
                        (viewModel::onEvent)(SignInEvent.PostLoginRequest(SignInData(username, password)))
                        navGraphController.navigate(Route.TechShopNavigation.route) {
                            popUpTo(Route.SignInScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewSignInScreen() {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    TechShopMobileTheme {
        Scaffold(
            topBar = {
                UtilityTopNavigation(
                    onRightBtnClick = { /*TODO*/ },
                    onLeftBtnClick = {},
                    leftBtnIcon = R.drawable.ic_cross,
                    title = "Đăng nhập",
                    onSearch = {})
            }
        ) {
            val topPadding = it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Blue_100)
                    .padding(top = topPadding)
                    .padding(horizontal = SmallPadding)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_computer),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = "Đăng nhập")
                    Row() {
                        Text(text = "Chưa có tài khoản?")
                        Text(text = "Đăng kí ngay!")
                    }
                    Input(
                        inputText = username,
                        placeHolderText = "Tài khoản",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        username = it
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    TransformableInput(
                        inputText = password,
                        placeHolderText = "Mật khẩu",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        password = it
                    }
                    Text(text = "Quên mật khẩu")
                    CustomButton(text = "Đăng nhập", modifier = Modifier.fillMaxWidth()) {

                    }
                }
            }

        }
    }
}

data class SignInForm(
    var userName: String = "",
    var password: String = "",
)