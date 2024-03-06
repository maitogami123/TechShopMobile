package com.vi.techshopmobile.presentation.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home_navigator.component.BottomNavigationItem
import com.vi.techshopmobile.presentation.home_navigator.component.TechShopBottomNavigation
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun HomeScreen() {
    Text(text = "HomeScreen")
}

@Preview()
@Composable
fun PreviewHomeScreen() {
    TechShopMobileTheme {
        HomeScreen()
    }
}