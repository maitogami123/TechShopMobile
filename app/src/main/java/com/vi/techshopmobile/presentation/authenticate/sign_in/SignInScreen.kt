package com.vi.techshopmobile.presentation.authenticate.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.presentation.Dimens.MediumPadding2
import com.vi.techshopmobile.presentation.Dimens.RadiusSmall
import com.vi.techshopmobile.presentation.Dimens.SmallGap
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

@Composable
fun SignInScreen(
    navGraphController: NavController,
    navController: NavController
) {
    val viewModel: AuthenticateViewModel = hiltViewModel()
    val isLoggedInState = viewModel.isLoggedIn.collectAsState();
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = isLoggedInState.value) {
        if (isLoggedInState.value) {
            navGraphController.navigate(Route.TechShopNavigation.route) {
                popUpTo(Route.SignInScreen.route) {
                    inclusive = true
                }
            }
        }
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
                    .clip(RoundedCornerShape(topStart = RadiusSmall, topEnd = RadiusSmall))
                    .background(Color.White)
                    .padding(horizontal = SmallPadding)
                    .padding(top = MediumPadding2, bottom = SmallPadding),
            ) {
                Text(
                    text = "Đăng nhập",
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(SmallGap))
                Row() {
                    Text(text = "Chưa có tài khoản?")
                    Text(text = " Đăng kí ngay!",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .clickable { navController.navigate(Route.SignUpScreen.route) })
                }
                Spacer(modifier = Modifier.height(SmallGap))
                Input(
                    inputText = username,
                    placeHolderText = "Tài khoản",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    username = it
                }
                Spacer(modifier = Modifier.height(SmallGap))
                TransformableInput(
                    inputText = password,
                    placeHolderText = "Mật khẩu",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    password = it
                }
                Spacer(modifier = Modifier.height(SmallGap))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        "Quên mật khẩu",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Route.ForgetPasswordScreenEmailScreen.route) },
                        textAlign = TextAlign.Right,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.height(SmallGap))
                CustomButton(text = "Đăng nhập", modifier = Modifier.fillMaxWidth()) {
                    (viewModel::onEvent)(
                        AuthenticateEvent.LoginEvent(
                            SignInData(
                                username,
                                password
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
                        .clip(RoundedCornerShape(topStart = RadiusSmall, topEnd = RadiusSmall))
                        .background(Color.White)
                        .padding(horizontal = SmallPadding)
                        .padding(top = 36.dp)
                ) {
                    Text(
                        text = "Đăng nhập",
                        style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(SmallGap))
                    Row() {
                        Text(text = "Chưa có tài khoản?")
                        Text(
                            text = " Đăng kí ngay!",
                            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.height(SmallGap))
                    Input(
                        inputText = username,
                        placeHolderText = "Tài khoản",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        username = it
                    }
                    Spacer(modifier = Modifier.height(SmallGap))
                    TransformableInput(
                        inputText = password,
                        placeHolderText = "Mật khẩu",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        password = it
                    }
                    Spacer(modifier = Modifier.height(SmallGap))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(text = "Quên mật khẩu")
                    }
                    Spacer(modifier = Modifier.height(SmallGap))
                    CustomButton(text = "Đăng nhập", modifier = Modifier.fillMaxWidth()) {}
                }
            }

        }
    }
}
