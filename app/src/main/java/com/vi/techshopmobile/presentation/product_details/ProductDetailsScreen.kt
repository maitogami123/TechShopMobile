package com.vi.techshopmobile.presentation.product_details

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home_navigator.component.BottomNavigationItem
import com.vi.techshopmobile.presentation.home_navigator.component.TechShopBottomNavigation
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation

@Composable
fun ProductDetailsScreen(
    productLine: String,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                rightBtnIcon = R.drawable.ic_share
            ) {
                onNavigateUp()
            }
        },
    ) {
        val paddingTop = it.calculateTopPadding()
        Text(text = productLine)
    }
}