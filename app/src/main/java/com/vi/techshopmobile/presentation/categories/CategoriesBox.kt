package com.vi.techshopmobile.presentation.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.vi.techshopmobile.R
import com.vi.techshopmobile.util.Constants

@Composable
fun CategoriesBox(modifier: Modifier = Modifier, categoryName: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(121.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            //model = painterResource(id = R.drawable.banner_category),
            painter = painterResource(id = R.drawable.banner_category),
            contentDescription = null,
            modifier = modifier.matchParentSize(),

            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = categoryName,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xffE1EFFE)
            )
//            TextButton(onClick = { /*TODO*/ }) {
//                Text(text = "Xem tất cả sản phẩm",
//                    style = MaterialTheme.typography.titleSmall,
//                    color = Color(0xffE1EFFE),
//                    textDecoration = TextDecoration.Underline
//                    )
//            }
            Text(
                modifier = Modifier.padding(top = 4.dp).clickable {  },
                text = "Xem tất cả sản phẩm",
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xffE1EFFE),
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Preview
@Composable
fun CategoriesBoxPreview() {
    CategoriesBox(categoryName = "Laptops")
}
