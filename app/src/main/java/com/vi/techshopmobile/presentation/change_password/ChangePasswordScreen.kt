package com.vi.techshopmobile.presentation.change_password
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.common.TransformableInput
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(onNavigateUp: () -> Unit){
    val viewModel: ChangePasswordViewModel = hiltViewModel()
    LaunchedEffect(key1 = null) {
    }
    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Đổi mật khẩu", leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column ( modifier = Modifier
            .padding(top = topPadding + Dimens.SmallPadding)
            .padding(horizontal = Dimens.SmallPadding),
        ){
            Text(
                text = ("Cung cấp mật khẩu mới"),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = SmallPadding)
            )
            TransformableInput(
                inputText = "hellu",
                labelText = "Nhập mật khẩu cũ",
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = SmallPadding)
            ){}
            TransformableInput(
                inputText = "helluuu",
                labelText = "Nhập mật khẩu mới",
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = SmallPadding)
            ){}
            TransformableInput(
                inputText = "helluuu",
                labelText = "Nhập lại mật khẩu mới",
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = SmallPadding)
            ){}
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(modifier = Modifier.fillMaxWidth(), text = "Thay đổi") {}
        }
    }
}