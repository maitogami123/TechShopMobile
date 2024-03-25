package com.vi.techshopmobile.presentation.cart.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.vi.techshopmobile.R
import com.vi.techshopmobile.data.remote.cart.CartResponse
import com.vi.techshopmobile.presentation.Dimens.IconSizeMedium
import com.vi.techshopmobile.presentation.Dimens.SmallGap
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.Constants
import com.vi.techshopmobile.util.formatPrice

/**
 * A ProductCart contain plus to increase , minus to decrease quantity of Product in Cart.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param cartResponse to show Cart
 * @param onPlusQuantity to increase quantity of each Product in Cart
 * @param onMinusQuantity to decrease quantity of each Product in Cart
 * @param onDeleteProduct to delete product in Cart
 */
@Composable
fun ProductCart(
    modifier: Modifier = Modifier,
    cartResponse: CartResponse,
    onPlusQuantity: () -> Unit,
    onMinusQuantity: () -> Unit,
    onDeleteProduct: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
        //verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = Constants.BASE_URL + "product/get-file?filePath=" + cartResponse.thumbnailUri,
            contentDescription = null,
            modifier = Modifier.size(96.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(.8f)
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = cartResponse.productName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
            Text(
                text = formatPrice(cartResponse.price),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                ),
                color = Danger
            )
            Row(
                modifier = Modifier.fillMaxHeight(.6f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // TODO: Make increase and decrease quantity buttons functional, create a state to store current quantity
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight()
//                    .alpha(if (statusMinusBtn) 1f else 0.4f)
//                   .clickable(enabled = statusMinusBtn) {
//                       onPlusQuantity()
//                   }
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .size(32.dp)
                        .background(color = Color(0xffE1EFFE), shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        style = MaterialTheme.typography.displaySmall.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600)
                        ),
                        text = cartResponse.quantity.toString(),
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    tint = Color(0xff1C64F2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight(),
//                    .alpha(if (statusPlusBtn) 1f else 0.4f)
//                    .clickable(enabled = statusPlusBtn) {
//                        quantity = quantity.plus(1)
//                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.2f), contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painterResource(id = R.drawable.ic_cross),
                contentDescription = null,
                modifier = Modifier.size(IconSizeMedium).clickable {
                    onDeleteProduct()
                }
            )
        }
    }
}


/**
 * A ProductCart contain info of Product in Cart.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param cartResponse to show Cart
 */
@Composable
fun ProductCart(
    modifier: Modifier = Modifier,
    cartResponse: CartResponse,
) {
    val textQuantity = buildAnnotatedString {
        append("Số lượng: ")
        withStyle(
            style = SpanStyle(
                color = Color(0xffFF3A28),
                fontSize = 14.sp
            )
        ) {
            append(cartResponse.quantity.toString())
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
        //verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = Constants.BASE_URL + "product/get-file?filePath=" + cartResponse.thumbnailUri,
            contentDescription = null,
            modifier = Modifier.size(96.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(.8f)
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = cartResponse.productName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
            Text(
                text = formatPrice(cartResponse.price),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                ),
                color = Danger
            )
            Row(
                modifier = Modifier.fillMaxHeight(.6f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp
                    ), text = textQuantity
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.2f), contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painterResource(id = R.drawable.ic_cross),
                contentDescription = null,
                modifier = Modifier.size(IconSizeMedium)
            )
        }
    }
}


/**
 * A ProductCartRow contain info of Product in Cart(row contain price and quantity).
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param cartResponse to show Cart
 */
@Composable
fun ProductCartRow(
    modifier: Modifier = Modifier,
    cartResponse: CartResponse,
) {
    val textQuantity = buildAnnotatedString {
        append("Số lượng: ")
        withStyle(
            style = SpanStyle(
                color = Color(0xffFF3A28),
                fontSize = 14.sp
            )
        ) {
            append(cartResponse.quantity.toString())
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = Constants.BASE_URL + "product/get-file?filePath=" + cartResponse.thumbnailUri,
            contentDescription = null,
            modifier = Modifier.size(96.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 16.dp, end = 0.dp, top = 8.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier=Modifier.fillMaxHeight(.5f).fillMaxWidth(),
                text = cartResponse.productName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
            Row(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = formatPrice(cartResponse.price),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    ),
                    color = Danger
                )
                Text(
                    text = textQuantity,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ProductCartPreview() {
    TechShopMobileTheme {
        Column(
            modifier = Modifier.padding(SmallPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductCart(
                onMinusQuantity = {},
                onPlusQuantity = {},
                onDeleteProduct = {},
                cartResponse = CartResponse(
                    quantity = 1,
                    productLine = "ACER1",
                    productName = "ACER NITRO 5 FVXS5 SAD 2023êqweqeqweqwe",
                    price = 2.129e+07,
                    thumbnailUri = R.drawable.banner_category.toString()
                ),
            )
            Divider()
            ProductCart(
                cartResponse = CartResponse(
                    quantity = 1,
                    productLine = "ACER1",
                    productName = "ACER NITRO 5 FVXS5 SAD 2023êqweqeqweqwe",
                    price = 2.129e+07,
                    thumbnailUri = R.drawable.banner_category.toString()
                ),
            )
            Divider()
            ProductCartRow(
                cartResponse = CartResponse(
                    quantity = 1,
                    productLine = "ACER1",
                    productName = "ACER NITRO 5 FVXS5 SAD 2023",
                    price = 2.129e+07,
                    thumbnailUri = R.drawable.banner_category.toString()
                ),
            )
        }
    }
}