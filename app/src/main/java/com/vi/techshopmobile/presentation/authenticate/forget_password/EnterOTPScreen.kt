package com.vi.techshopmobile.presentation.authenticate.forget_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route

@Composable
fun EnterOTPScreen(navController: NavController, onNavigateUp: () -> Unit) {
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
        Column (
            modifier = Modifier.padding(top = topPadding)
        ) {
            Text(
                text = "Please enter OTP in your email",
                modifier = Modifier.padding(top = topPadding)
            )
            Button(onClick = {
                navController.navigate(Route.ForgetPasswordScreenNewPasswordScreen.route)
            }) {
                Text(text = "Tiếp tục")
            }
        }
    }
}