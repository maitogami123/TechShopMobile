package com.vi.techshopmobile.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vi.techshopmobile.presentation.categories.components.CategoryBadge

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesSection(modifier: Modifier = Modifier) {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 0.dp),
            text = "Danh mục sản phẩm",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 24.sp ,
            fontWeight = FontWeight.Bold
        )
        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            maxItemsInEachRow = 4,
        ) {
            state.categories.forEach { category ->
                CategoryBadge(category = category)
            }
        }
    }
}

@Preview
@Composable
fun CategoriesScreenPreview() {
    CategoriesSection()
}