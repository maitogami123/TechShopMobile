package com.vi.techshopmobile.presentation.checkout.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.Provinces
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.checkout.components.CheckBoxText
import com.vi.techshopmobile.presentation.checkout.components.ComboBox
import com.vi.techshopmobile.presentation.common.AddressSelected
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.FloatingOnBottomBar
import com.vi.techshopmobile.presentation.common.InputInfo
import com.vi.techshopmobile.presentation.common.InputInfoNoUnder
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.ui.theme.Blue_100
import com.vi.techshopmobile.ui.theme.Blue_200
import com.vi.techshopmobile.ui.theme.Blue_50
import com.vi.techshopmobile.util.formatPhoneNumber

@Composable
fun AddNewAddressScreen(
    navController: NavController,
    onNavigateUp: () -> Unit
) {

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    var city by remember {
        mutableStateOf("")
    }
    var district by remember {
        mutableStateOf("")
    }
    var detailedAddress by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = {
                    onNavigateUp()
                },
                leftBtnIcon = R.drawable.ic_left_arrow,
                title = "Thêm địa chỉ mới",
                onSearch = {})
        },
        bottomBar = {
            FloatingBottomBar(buttonText = "Thêm mới",
                onButtonClick = {

                }
            )
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .background(Blue_50)
                    .padding(vertical = Dimens.SmallGap)
                    .fillMaxWidth()
                    .padding(start = Dimens.SmallGap),
                text = "Liên hệ"
            )
            Row {
                InputInfo(
                    inputText = "",
                    placeHolderText = "Họ",
                    modifier = Modifier.fillMaxWidth(.5f)
                ) {
                    lastName = it
                }
                Spacer(modifier = Modifier.width(Dimens.SmallGap))
                InputInfo(
                    inputText = "",
                    placeHolderText = "Tên",
                ) {
                    firstName = it
                }
            }

            InputInfoNoUnder(
                inputText = "",
                placeHolderText = "Số điện thoại",
                modifier = Modifier.fillMaxWidth()
            ) {
                number = it
            }
            Spacer(modifier = Modifier.height(Dimens.SmallPadding))
            Text(
                modifier = Modifier
                    .background(Blue_50)
                    .padding(vertical = Dimens.SmallGap)
                    .fillMaxWidth()
                    .padding(start = Dimens.SmallGap),
                text = "Địa chỉ"
            )
            ComboBox(placeHolderText = "Tỉnh")
            ComboBox(placeHolderText = "Quận/Huyện")
            InputInfoNoUnder(
                inputText = "",
                placeHolderText = "Số nhà, tên đường",
                modifier = Modifier.fillMaxWidth()
            ) {
                detailedAddress = it
            }

            Spacer(modifier = Modifier.height(Dimens.SmallPadding))
            Text(
                modifier = Modifier
                    .background(Blue_50)
                    .padding(vertical = Dimens.SmallGap)
                    .fillMaxWidth()
                    .padding(start = Dimens.SmallGap),
                text = "Địa chỉ"
            )

            Spacer(modifier = Modifier.height(Dimens.SmallPadding))
            CheckBoxText(
                title = "Đặt làm địa chỉ mặc định",
                isChecked = false
            ) {}
        }
    }
}

@Preview
@Composable
fun AddNewAddressScreenScreen() {
    AddNewAddressScreen(navController = rememberNavController()) {

    }

}