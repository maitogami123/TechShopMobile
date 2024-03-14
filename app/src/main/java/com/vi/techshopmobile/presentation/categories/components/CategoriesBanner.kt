package com.vi.techshopmobile.presentation.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R

@Composable
fun CategoriesBanner(modifier: Modifier = Modifier, categoryName: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(121.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_category),
            contentDescription = null,
            modifier = modifier.matchParentSize(),
            contentScale = ContentScale.Crop
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
    CategoriesBanner(categoryName = "Laptops")
}
