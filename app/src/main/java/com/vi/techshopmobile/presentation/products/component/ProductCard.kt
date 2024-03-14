package com.vi.techshopmobile.presentation.products.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.DarkDanger
import com.vi.techshopmobile.util.Constants
import com.vi.techshopmobile.util.formatPrice

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ProductLine
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(modifier = Modifier) {
            AsyncImage(
                model = Constants.BASE_URL + "product/get-file?filePath=" + product.thumbnailUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .align(Alignment.CenterHorizontally)
                //.aspectRatio(1f),
                , contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                text = product.productName, style = TextStyle(
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    lineHeight = MaterialTheme.typography.labelMedium.lineHeight,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 20.dp),
                text = formatPrice(product.price),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                    lineHeight = MaterialTheme.typography.displaySmall.lineHeight,
                    fontWeight = MaterialTheme.typography.displaySmall.fontWeight
                ),
                color = Danger
            )
        }
    }
}