package com.vi.techshopmobile.presentation.user_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.RadiusMedium
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.user_setting.components.SettingItem
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun UserSettingScreen(navController: NavController) {

    val viewModel: UserSettingViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Column {
            SettingItem(R.drawable.ic_shopping_cart, "Đơn hàng") {

            }
            SettingItem(R.drawable.ic_user, "Thông tin cá nhân") {

            }
            SettingItem(R.drawable.ic_location, "Địa chỉ") {

            }
            SettingItem(R.drawable.ic_heart, "Sản phẩm yêu thích") {
                navController.navigate(Route.WishListScreen.route)
            }
            SettingItem(R.drawable.ic_bell, "Về TechShop") {

            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                (viewModel::onEvent)(UserSettingEvent.LogoutEvent)
            }, modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(RadiusMedium)), colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Box(Modifier.padding(18.dp, 12.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_up_arrow),
                    contentDescription = null
                )
                Text(
                    text = "Logout",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun PreviewUserSettingScreen() {
    TechShopMobileTheme {
//        UserSettingScreen()
    }
}