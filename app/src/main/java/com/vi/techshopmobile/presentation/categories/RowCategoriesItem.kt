package com.vi.techshopmobile.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.domain.model.Category

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RowCategoriesItem(modifier: Modifier = Modifier, categories: List<Category>) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
// verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalArrangement = Arrangement.SpaceBetween,

        maxItemsInEachRow = 4,
    ) {
        categories.forEach { category ->
            ItemCategory(categories = category)
        }
    }
}

@Preview
@Composable
fun RowCategoriesItemPreview() {
    RowCategoriesItem(
        categories = listOf(
            Category(id = 1, name = "Computer", brands = emptyList()),
            Category(id = 2, name = "Laptop", brands = emptyList()),
            Category(id = 3, name = "Mouse", brands = emptyList()),
            Category(id = 4, name = "Monitor", brands = emptyList()),
        )
    )
}