package com.vi.techshopmobile.presentation.home_navigator.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.RadiusLarge
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

/**
 * Main top-bar for TechShop Mobile application.
 *
 * Contain TechShop logo and login/register or user shopping cart
 *
 * @param isLoggedIn check if the user is logged in to display login/register or current logged in user's cart
 * @param onClick the function to handle navigate to login/register screen or user's cart
 */
@Composable
fun MainTopNavigation(
    isLoggedIn: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = SmallPadding, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_techshop_logo),
            contentDescription = null
        )
        if (!isLoggedIn)
            Text(
                modifier = Modifier
                    .clickable {
                        onClick()
                    },
                textAlign = TextAlign.Right,
                text = "Đăng nhập/Đăng ký",
                style = MaterialTheme.typography.displaySmall
            )
        else
            ShoppingCartButton(items = 5, onClick)
        // TODO: Create a table in room to store user cart item.
    }
}

/**
 *
 * A custom IconButton made specifically for user's shopping cart
 *
 * @param items the number of items in user's cart
 * @param onClick the function to handle navigate to user's cart
 */
@Composable
fun ShoppingCartButton(
    items: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(
                RoundedCornerShape(RadiusLarge)
            )
            .clickable {
                onClick()
            }
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp, 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                tint = Color.White,
                contentDescription = null
            )
            Text(
                text = if (items > 9) "9+" else "$items",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(2.dp, (-2).dp)
                    .clip(CircleShape)
                    .background(Danger)
                    .size(12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall.copy(
                    color = Color.White,
                    fontSize = 8.sp
                )
            )
        }
        Text(
            modifier = Modifier.padding(2.dp, 0.dp, 0.dp, 0.dp),
            text = "Giỏ hàng",
            style = MaterialTheme.typography.displaySmall.copy(color = Color.White)
        )
    }
}

@Preview
@Composable
fun PreviewShoppingCartBtn() {
    TechShopMobileTheme {
        ShoppingCartButton(items = 5, onClick = {})
    }
}

@Preview
@Composable
fun PreviewMainTopNavigation() {
    TechShopMobileTheme {
        Column(
            Modifier.fillMaxSize()
        ) {
            MainTopNavigation(onClick = {})
            MainTopNavigation(onClick = {}, isLoggedIn = true)
        }
    }
}