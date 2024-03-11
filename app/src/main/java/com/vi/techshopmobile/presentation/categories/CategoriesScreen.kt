package com.vi.techshopmobile.presentation.categories

import android.media.RouteListingPreference.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.size.Dimension
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.products.ProductsViewModel

@Composable
fun CategoriesScreen(modifier: Modifier = Modifier) {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
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
        RowCategoriesItem(categories = state.categories)
        //Spacer(modifier = Modifier.height(Dimens.SmallPadding))
    }
}

@Preview
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen(
//        categories = listOf(
//            Category(id = 1, name = "Computer", brands = emptyList()),
//            Category(id = 2, name = "Laptop", brands = emptyList()),
//            Category(id = 3, name = "Laptop Gaming", brands = emptyList()),
//            Category(id = 4, name = "Monitor", brands = emptyList()),
//        )
    )
}