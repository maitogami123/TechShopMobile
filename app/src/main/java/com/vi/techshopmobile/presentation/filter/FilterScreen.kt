package com.vi.techshopmobile.presentation.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.filter.component.FilterCheckBoxGroup
import com.vi.techshopmobile.presentation.filter.component.FilterRadioGroup
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.ui.theme.Blue_50

data class FilterLabel(
    val label: String
)

@Composable
fun FilterScreen(navController: NavController, brands: List<String>, onNavigateUp: () -> Unit) {
    val filterPrice = listOf(
        FilterLabel(label = "Từ 0 - 10.000.000 VNĐ"),
        FilterLabel(label = "Từ 10.000.000 - 15.000.000 VNĐ"),
        FilterLabel(label = "Từ 15.000.000 - 20.000.000 VNĐ"),
        FilterLabel(label = "Từ 20.000.000 - 30.000.000 VNĐ"),
        FilterLabel(label = "Từ 30.000.000 trở lên")
    )
    val filterOption = listOf(
        FilterLabel(label = "Sắp xếp theo giá từ thấp tới cao dần"),
        FilterLabel(label = "Sắp xếp theo giá từ cao tới thấp dần"),
        FilterLabel(label = "Sắp xếp từ A - Z"),
        FilterLabel(label = "Sắp xếp tù Z - A")
    )
    val filterBrand = brands.map {
        FilterLabel(label = it)
    }

    var radioSelectedBrandIndex by remember {
        mutableIntStateOf(-1)
    }
    val disableItemRadio = listOf<Int>()
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                leftBtnIcon = R.drawable.ic_cross,
                title = "Filter Product",
                onSearch = {})
        },
        bottomBar = {
            FloatingBottomBar(buttonText = "Xác nhận", onButtonClick = {})
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding() + 16.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Blue_50)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                item {
                    FilterRadioGroup(heading = "Sắp xếp sản phẩm theo", optionList = filterOption)
                }
                item {
                    FilterCheckBoxGroup(heading = "Giá trị sản phẩm", optionList = filterPrice)
                }
                if (brands.isNotEmpty()) {
                    item {
                        FilterCheckBoxGroup(
                            heading = "Hãng sản phẩm",
                            optionList = filterBrand
                        )
                    }
                }

            }
        }
    }
}