package com.vi.techshopmobile.presentation.products.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.Dimens.IconSizeLarge
import com.vi.techshopmobile.presentation.home.home_navigator.LocalNavController
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToDetails
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.Gray_500
import com.vi.techshopmobile.ui.theme.Info
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.Constants
import com.vi.techshopmobile.util.formatPrice

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ProductLine
) {
    val navController = LocalNavController.current;

    Card(
        modifier = modifier
            .clickable {
                navigateToDetails(navController, product.productLine)
            }
            .width(180.dp)
            .height(250.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column() {
            ConstraintLayout {
                val (icon) = createRefs()
                AsyncImage(
                    model = Constants.BASE_URL + "product/get-file?filePath=" + product.thumbnailUri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                if (product.discount > 0)
                    Icon(
                        painterResource(id = R.drawable.ic_discount_tag),
                        contentDescription = null,
                        modifier = Modifier
                            .constrainAs(icon) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                            .size(IconSizeLarge),
                        tint = Danger
                    )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) content_col@{
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = product.productName, style = MaterialTheme.typography.headlineMedium
                )

                Column {
                    if (product.stock == 0) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Liên hệ",
                            style = MaterialTheme.typography.displaySmall,
                            color = Info
                        )
                        return@content_col
                    }
                    if (product.discount > 0) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = formatPrice(product.price),
                            style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough),
                            color = Gray_500
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = formatPrice(product.price),
                            style = MaterialTheme.typography.displaySmall,
                            color = Danger
                        )
                    } else {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = formatPrice(product.price),
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            }

        }
    }
}

@Preview
@Composable
private fun PreviewProductCard() {
    TechShopMobileTheme {
        ProductCard(
            product = ProductLine(
                brandName = "",
                createdAt = "",
                deletedAt = "",
                productLine = "AcerNitro5",
                productName = "EX DISPLAY : MSI Pro 16 Flex-036AU 15.6 MULTITOUCH",
                discount = 10.0,
                price = 100000.0,
                categoryName = "",
                id = 123,
                stock = 1,
                thumbnailUri = "thumbnails/testProduct2/testProduct2.jpg"
            )
        )
    }
}