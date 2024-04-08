package com.vi.techshopmobile.presentation.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.ui.theme.Blue_100

@Composable
fun FilterScreen(navController: NavController, onNavigateUp: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                leftBtnIcon = R.drawable.ic_cross,
                title = "Filter Product",
                onSearch = {})
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top=it.calculateTopPadding() + 16.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)).background(Blue_100)
        ) {
            LazyColumn( modifier= Modifier.padding(16.dp)
            ) {

            }
        }
    }
}