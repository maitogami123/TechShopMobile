package com.vi.techshopmobile.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.techshop_navigator.component.BottomNavigationItem
import com.vi.techshopmobile.presentation.techshop_navigator.component.TechShopBottomNavigation
import com.vi.techshopmobile.presentation.techshop_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun HomeScreen() {
    var selectedItem by rememberSaveable {
        mutableIntStateOf(1)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            TechShopBottomNavigation(
                items = listOf(
                    BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_user, text = "User"),
            ),
                selectedItem = selectedItem,
                onItemClick = { index ->
                    selectedItem = index
                })
        },
        topBar = {
            UtilityTopNavigation(
                leftBtnIcon = R.drawable.ic_left_arrow,
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { /*TODO*/ }) {
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        val topPadding = it.calculateTopPadding()
        Text(text = "HomeScreen", modifier = Modifier.padding(0.dp, topPadding, 0.dp, 0.dp))
    }
}

@Preview()
@Composable
fun PreviewHomeScreen() {
    TechShopMobileTheme {
        HomeScreen()
    }
}