package com.vi.techshopmobile.presentation.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.presentation.Dimens

@Composable
fun ItemCategory(modifier: Modifier = Modifier,categories: Category){
    Box(
        modifier = modifier.height(92.dp).width(83.dp),
    )
    {
        Column(modifier = modifier.background(Color.White).fillMaxSize()) {
            Image(
                Icons.Filled.Favorite, contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color(0xff1A56DB)),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xffEBF5FF), shape = RoundedCornerShape(10.dp))
                    .padding(19.dp, 7.dp)
                    .size(Dimens.IconSizeExtraLarge)
            )
            Text(
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically,true)
                    .fillMaxWidth()
                    .padding(10.dp).background(Color.White),
                text = categories.name,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ItemCategoryPreview(){
    ItemCategory(categories = Category(name = "Computer Gaming", brands = emptyList(), id = 1))
}