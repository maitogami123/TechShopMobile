package com.vi.techshopmobile.presentation.user_setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserSettingScreen() {

    val viewModel: UserSettingViewModel = hiltViewModel()

    Column {
        Text(text = "User setting screen")
        Button(onClick = {
            (viewModel::onEvent)(UserSettingEvent.LogoutEvent)
        }) {
            Text(text = "Logout")
        }
    }
}