package com.vi.techshopmobile.presentation.filter

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.Brand
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.checkout.LocalSelectedIndex
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.filter.component.FilterCheckBoxGroup
import com.vi.techshopmobile.presentation.filter.component.FilterRadioGroup
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToProducts
import com.vi.techshopmobile.presentation.products.ProductsViewModel
import com.vi.techshopmobile.ui.theme.Blue_50

@Composable
fun FilterScreen(
    navController: NavController,
    category: String,
    brands: ArrayList<Brand>,
    products: List<ProductLine>,
    onNavigateUp: () -> Unit,
    ) {
    val filterPrice = listOf(
        FilterPrice(
            index = 1,
            labelName = "Từ 0 - 10.000.000 VNĐ",
            valueStart = 0.0,
            valueEnd = 10000000.0
        ),
        FilterPrice(
            index = 2,
            labelName = "Từ 10.000.000 - 15.000.000 VNĐ",
            valueStart = 10000000.0,
            valueEnd = 15000000.0
        ),
        FilterPrice(
            index = 3,
            labelName = "Từ 15.000.000 - 20.000.000 VNĐ",
            valueStart = 15000000.0,
            valueEnd = 20000000.0
        ),
        FilterPrice(
            index = 4,
            labelName = "Từ 20.000.000 - 30.000.000 VNĐ",
            valueStart = 20000000.0,
            valueEnd = 30000000.0
        ),
        FilterPrice(
            index = 5,
            labelName = "Từ 30.000.000 trở lên",
            valueStart = 3000000.0,
            valueEnd = Double.MAX_VALUE
        )
    )
    val filterOption = listOf(
        FilterLabel(value = "1", label = "Sắp xếp theo giá từ thấp tới cao dần"),
        FilterLabel(value = "2", label = "Sắp xếp theo giá từ cao tới thấp dần"),
        FilterLabel(value = "3", label = "Sắp xếp từ A - Z"),
        FilterLabel(value = "4", label = "Sắp xếp tù Z - A")
    )
    val filterBrand = brands.map {
        FilterBrand(brand = it.brandName, products = it.products)
    }

    val viewModel: FilterViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(
            FilterEvent.GetProducts(products)
        )
    }

    var initialSelectedOptions = LocalSelectedIndex.current
    val selectedOptions = remember {
        mutableStateOf(initialSelectedOptions.value)
    }

    var initialSeclectedPriceOption = LocalSelectedIndex.current
    var checkItemPricePos by remember {
        mutableStateOf(listOf<Int>())
    }
    var checkItemBrandPos by remember {
        mutableStateOf(listOf<Int>())
    }
    var selectedIndexOptionSelected by remember {
        mutableIntStateOf(-1)
    }
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
            FloatingBottomBar(
                buttonText = "Xác nhận",
                onButtonClick = {
                    initialSelectedOptions.intValue = selectedOptions.value
                    if (brands.size == 1) navigateToProducts(
                        navController,
                        category,
                        brands[0].brandName, state.products, isFilter = true
                    ) else navigateToProducts(navController, category, "", state.products, isFilter = true)
                })
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
                    Column(
                        modifier = Modifier
                            .selectableGroup()
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Sắp xếp sản phẩm theo",
                            style = MaterialTheme.typography.displayMedium
                        )
                        filterOption.forEachIndexed { index, filterLabel ->
                            FilterRadioGroup(
                                index = index,
                                lableName = filterLabel.label,
                                selectedIndex = selectedIndexOptionSelected,
                                onClick = {
                                    selectedIndexOptionSelected = index
                                    (viewModel::onEvent)(
                                        FilterEvent.FilterProductsByOption(
                                            selectedIndexOptionSelected,
                                            products = products,
                                            value = filterLabel.value
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .selectableGroup()
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Giá trị sản phẩm",
                            style = MaterialTheme.typography.displayMedium
                        )
                        filterPrice.forEachIndexed { _, filterLabel ->
                            FilterCheckBoxGroup(
                                index = filterLabel.index,
                                lableName = filterLabel.labelName,
                                checkItemPos = checkItemPricePos,
                                onClick = {
                                    checkItemPricePos =
                                        if (checkItemPricePos.contains(filterLabel.index)) {
                                            checkItemPricePos - filterLabel.index
                                        } else {
                                            checkItemPricePos + filterLabel.index
                                        }
                                    (viewModel::onEvent)(
                                        FilterEvent.FilterProductByPrice(
                                            checkItemPricePos,
                                            products = state.products,
                                            filterLabel.valueStart,
                                            filterLabel.valueEnd
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
                if (brands.size > 1) {
                    item {
                        Column(
                            modifier = Modifier
                                .selectableGroup()
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Hãng sản xuất",
                                style = MaterialTheme.typography.displayMedium
                            )
                            filterBrand.forEachIndexed { index, filterLabel ->
                                FilterCheckBoxGroup(
                                    index = index,
                                    lableName = filterLabel.brand,
                                    checkItemPos = checkItemBrandPos,
                                    onClick = {
                                        checkItemBrandPos =
                                            if (checkItemBrandPos.contains(index)) {
                                                checkItemBrandPos - index
                                            } else {
                                                checkItemBrandPos + index
                                            }
                                        (viewModel::onEvent)(
                                            FilterEvent.FilterProductsByBrand(
                                                checkItemBrandPos,
                                                products = filterLabel.products,
                                                value = filterLabel.brand
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}