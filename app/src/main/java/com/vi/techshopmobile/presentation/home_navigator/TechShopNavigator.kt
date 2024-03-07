package com.vi.techshopmobile.presentation.home_navigator

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.MainViewModel
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home.HomeScreen
import com.vi.techshopmobile.presentation.home_navigator.component.BottomNavigationItem
import com.vi.techshopmobile.presentation.home_navigator.component.MainTopNavigation
import com.vi.techshopmobile.presentation.home_navigator.component.TechShopBottomNavigation
import com.vi.techshopmobile.presentation.home_navigator.component.UserInformation
import com.vi.techshopmobile.presentation.home_navigator.component.UserTopNavigation
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
    val viewModel: TechShopNavigatorViewModel = hiltViewModel()
    val isLoggedIn = viewModel.accessToken.isNotBlank();
    Log.d("Token", viewModel.accessToken)
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
            if (selectedItem == 2 && isLoggedIn) {
                UserTopNavigation(userInfo = UserInformation("test", "test", "https://scontent.fhan3-3.fna.fbcdn.net/v/t39.30808-6/305108279_3380406918948385_1634056947920206356_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=5f2048&_nc_ohc=tS-Z-Hvlq1wAX9jI0iS&_nc_ht=scontent.fhan3-3.fna&oh=00_AfC9TMejNOQxjC-mEvAN4OOC-SxCU1xMXdhpjQLCQqDZDA&oe=65EE03B1"))
            } else {
                MainTopNavigation(onClick = {
                    navGraphController.navigate(Route.AuthenticateNavigation.route)
                }, isLoggedIn = isLoggedIn)
            }
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