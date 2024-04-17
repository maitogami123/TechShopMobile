package com.vi.techshopmobile.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.ui.theme.Blue_200
import com.vi.techshopmobile.ui.theme.Blue_500
import com.vi.techshopmobile.ui.theme.Gray_700
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

/**
 * A floating bar contain wishlist, cart, text button.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param itemInWishList to check if icon heart need to be filled or not
 * @param onAddToWishList to add or remove item inside wish list
 * @param onAddToCart add item to cart
 * @param buttonText big button text
 * @param onButtonClick handle the big button functionality
 */

@Composable
fun FloatingBottomBar(
    modifier: Modifier = Modifier,
    itemInWishList: Boolean = false,
    onAddToWishList: () -> Unit,
    onAddToCart: () -> Unit,
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(SmallPadding)
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Button(
            onClick = { onAddToWishList() }, modifier = Modifier.fillMaxHeight(),
            shape = RoundedCornerShape(0),
            colors = ButtonDefaults.buttonColors(containerColor = Blue_500)
        ) {
            Icon(
                painter = painterResource(id = if (!itemInWishList) R.drawable.ic_heart else R.drawable.ic_heart_filled),
                contentDescription = null
            )
        }
        Divider(
            modifier = Modifier
                .background(Blue_500)
                .fillMaxHeight()
                .padding(horizontal = 0.dp, vertical = SmallPadding)
                .width(1.dp),
            color = Gray_700
        )
        Button(
            onClick = { onAddToCart() }, modifier = Modifier.fillMaxHeight(),
            shape = RoundedCornerShape(0),
            colors = ButtonDefaults.buttonColors(containerColor = Blue_500)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = null
            )
        }
        Button(
            onClick = { onButtonClick() }, modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp)
                .weight(1f),
            shape = RoundedCornerShape(0)
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * A floating bar contain wishlist icon and text button.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param itemInWishList to check if icon heart need to be filled or not
 * @param onAddToWishList to add or remove item inside wish list
 * @param buttonText big button text
 * @param onButtonClick handle the big button functionality
 */

@Composable
fun FloatingBottomBar(
    modifier: Modifier = Modifier,
    buttonText: String,
    itemInWishList: Boolean = false,
    onAddToWishList: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(SmallPadding)
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Button(
            onClick = { onAddToWishList() }, modifier = Modifier.fillMaxHeight(),
            shape = RoundedCornerShape(0),
            colors = ButtonDefaults.buttonColors(containerColor = Blue_500)
        ) {
            Icon(
                painter = painterResource(id = if (!itemInWishList) R.drawable.ic_heart else R.drawable.ic_heart_filled),
                contentDescription = null
            )
        }
        Button(
            onClick = { onButtonClick() }, modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp)
                .weight(1f),
            shape = RoundedCornerShape(0)
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * A floating bar contain only text button.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param buttonText big button text
 * @param onButtonClick handle the big button functionality
 */

@Composable
fun FloatingBottomBar(
    modifier: Modifier = Modifier,
    buttonText: String,
    onButtonClick: () -> Unit,
    enable: Boolean = true
) {
    Row(
        modifier = modifier
            .padding(SmallPadding)
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(if(enable)MaterialTheme.colorScheme.primary else Color.Gray)
    ) {
        Button(
            enabled = enable,
            onClick = { onButtonClick() }, modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp)
                .weight(1f),
            shape = RoundedCornerShape(0)
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FloatingOnBottomBar(
    modifier: Modifier = Modifier,
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(SmallPadding)
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Button(
            onClick = { onButtonClick() }, modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp)
                .weight(1f),
            shape = RoundedCornerShape(0),
            colors = ButtonDefaults.buttonColors(containerColor = Blue_200)
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0xff2B2B2B)
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewFloatingBottomBar() {
    TechShopMobileTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            FloatingBottomBar(buttonText = "Mua ngay", onButtonClick = {}, onAddToWishList = {}, onAddToCart = {})
            FloatingBottomBar(buttonText = "Liên hệ", onButtonClick = {}, onAddToWishList = {}, itemInWishList = true)
            FloatingBottomBar(buttonText = "Xác nhận", onButtonClick = {})
            FloatingOnBottomBar(buttonText = "+ Thêm dịa chỉ mới", onButtonClick = {})

        }
    }
}