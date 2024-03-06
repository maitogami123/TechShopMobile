package com.vi.techshopmobile.presentation.authenticate.sign_in

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route

@Composable
fun SignInScreen(
    navGraphController: NavController,
    navController: NavController
) {
    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = {
                    navGraphController.navigate(Route.TechShopNavigation.route) {
                        popUpTo(Route.AppStartNavigation.route)
                    }
                },
                leftBtnIcon = R.drawable.ic_cross,
                title = "Đăng nhập",
                onSearch = {})
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier.padding(top = topPadding)
        ) {
            Text(
                "Dang ki",
                modifier = Modifier
                    .clickable { navController.navigate(Route.SignUpScreen.route) })
            Text(
                "Quên mật khẩu",
                modifier = Modifier
                    .clickable { navController.navigate(Route.ForgetPasswordScreenEmailScreen.route) })
        }

    }
}