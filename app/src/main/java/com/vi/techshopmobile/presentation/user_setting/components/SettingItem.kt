package com.vi.techshopmobile.presentation.user_setting.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.IconSizeMedium
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun SettingItem(
    @DrawableRes icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onClick();
        },
    ) {
        Row(
            modifier = Modifier.padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                Modifier.size(IconSizeMedium)
            )
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineMedium
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null
            )
        }
        Divider()
    }
}

@Preview
@Composable
private fun PreviewSettingItem() {
    TechShopMobileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
//            SettingItem(R.drawable.ic_shopping_cart, "Đơn hàng")
//            SettingItem(R.drawable.ic_user, "Thông tin cá nhân")
//            SettingItem(R.drawable.ic_location, "Địa chỉ")
        }
    }
}