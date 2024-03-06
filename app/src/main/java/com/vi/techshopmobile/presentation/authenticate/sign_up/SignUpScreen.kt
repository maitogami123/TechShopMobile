package com.vi.techshopmobile.presentation.authenticate.sign_up

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation

@Composable
fun SignUpScreen(onNavigateUp: () -> Unit) {
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
        Text("Dang ki", Modifier.padding(top = topPadding))
    }
}