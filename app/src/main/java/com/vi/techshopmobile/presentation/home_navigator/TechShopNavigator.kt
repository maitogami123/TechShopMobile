package com.vi.techshopmobile.presentation.home_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home.HomeScreen
import com.vi.techshopmobile.presentation.home_navigator.component.BottomNavigationItem
import com.vi.techshopmobile.presentation.home_navigator.component.MainTopNavigation
import com.vi.techshopmobile.presentation.home_navigator.component.TechShopBottomNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.search.SearchScreen
import com.vi.techshopmobile.presentation.user_setting.UserSettingScreen
import com.vi.techshopmobile.util.navigateToTap

@Composable
fun HomeNavigator(navGraphController: NavController) {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_user, text = "User"),
        )
    }

    val navController = rememberNavController();
    val backStackState = navController.currentBackStackEntryAsState().value;
    var selectedItem by rememberSaveable {
        mutableIntStateOf(1)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.SearchScreen.route -> 0
            Route.HomeScreen.route -> 1
            Route.UserSettingScreen.route -> 2
            else -> 1
        }
    }

    Scaffold(
        topBar = {
            MainTopNavigation(onClick = {
                navGraphController.navigate(Route.AuthenticateNavigation.route)
            })
        },
        bottomBar = {
            TechShopBottomNavigation(
                items = bottomNavigationItem,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTap(navController, Route.SearchScreen.route)
                        1 -> navigateToTap(navController, Route.HomeScreen.route)
                        2 -> navigateToTap(navController, Route.UserSettingScreen.route)
                    }
                })
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        val topPadding = it.calculateTopPadding()
        NavHost(
            modifier = Modifier.padding(bottom = bottomPadding, top = topPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen()
            }
            composable(route = Route.SearchScreen.route) {
                SearchScreen()
            }
            composable(route = Route.UserSettingScreen.route) {
                UserSettingScreen()
            }
        }
    }
}