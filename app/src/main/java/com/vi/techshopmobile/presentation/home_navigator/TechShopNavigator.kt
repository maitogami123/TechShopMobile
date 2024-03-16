package com.vi.techshopmobile.presentation.home_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
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
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home.HomeScreen
import com.vi.techshopmobile.presentation.home_navigator.component.BottomNavigationItem
import com.vi.techshopmobile.presentation.home_navigator.component.MainTopNavigation
import com.vi.techshopmobile.presentation.home_navigator.component.TechShopBottomNavigation
import com.vi.techshopmobile.presentation.home_navigator.component.UserInformation
import com.vi.techshopmobile.presentation.home_navigator.component.UserTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.product_details.ProductDetailsScreen
import com.vi.techshopmobile.presentation.search.SearchScreen
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.presentation.user_setting.UserSettingScreen
import com.vi.techshopmobile.presentation.wish_list_screen.WishListScreen
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.decodeToken
import com.vi.techshopmobile.util.navigateToTap

val LocalNavController = compositionLocalOf<NavController> {
    error("No LocalNavController provided")
}

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
    val navController = rememberNavController();
    val backStackState = navController.currentBackStackEntryAsState().value;
    var selectedItem by rememberSaveable {
        mutableIntStateOf(1)
    }
    val decodedToken = decodeToken(LocalToken.current)

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.SearchScreen.route -> 0
            Route.HomeScreen.route -> 1
            Route.UserSettingScreen.route -> 2
            Route.ProductDetailsScreen.route -> 3
            else -> 1
        }
    }

    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold(
            topBar = {
                if (selectedItem == 2 && isLoggedIn) {
                    // TODO: Fetch user information
                    UserTopNavigation(
                        userInfo = UserInformation(
                            decodedToken.sub,
                            "placeholder@domain.com",
                            "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/9d58f5a8-7784-442e-80b7-1f17231cb636/dgkoqel-0d33ec31-5bf0-4164-93e0-3d0f58913a91.jpg/v1/fit/w_808,h_808,q_70,strp/aporia_by_cogwurx_dgkoqel-414w-2x.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9ODA4IiwicGF0aCI6IlwvZlwvOWQ1OGY1YTgtNzc4NC00NDJlLTgwYjctMWYxNzIzMWNiNjM2XC9kZ2tvcWVsLTBkMzNlYzMxLTViZjAtNDE2NC05M2UwLTNkMGY1ODkxM2E5MS5qcGciLCJ3aWR0aCI6Ijw9ODA4In1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.5P7jBM1aKS7F0a1p9aI0DuFSH5IMq84T5rACbsgFCSY"
                        )
                    )
                } else if (selectedItem == 1) {
                    MainTopNavigation(onClick = {
                        navGraphController.navigate(Route.AuthenticateNavigation.route)
                    }, isLoggedIn = isLoggedIn)
                }
            },
            bottomBar = {
                if (selectedItem != 3)
                    TechShopBottomNavigation(
                        items = bottomNavigationItem,
                        selectedItem = selectedItem,
                        onItemClick = { index ->
                            when (index) {
                                0 -> navigateToTap(navController, Route.SearchScreen.route)
                                1 -> navigateToTap(navController, Route.HomeScreen.route)
                                2 -> {
                                    if (isLoggedIn)
                                        navigateToTap(navController, Route.UserSettingScreen.route)
                                    else {
                                        viewModel.sendEvent(Event.Toast("Vui lòng đăng nhập"))
                                    }
                                }
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
                    UserSettingScreen(navController)
                }
                composable(route = Route.ProductDetailsScreen.route) {
                    navController.previousBackStackEntry?.savedStateHandle?.get<String?>("productLine")
                        ?.let { productLine ->
                            ProductDetailsScreen(productLine) { navController.navigateUp() }
                        }
                }

                // TODO: Move this to another nav host
                // (suggestion: create a userSetting navHost to navigate between screens within user settings.)
                composable(route = Route.WishListScreen.route) {
                    WishListScreen()
                }
            }
        }
    }
}

fun navigateToDetails(navController: NavController, productLine: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("productLine", productLine)
    navController.navigate(Route.ProductDetailsScreen.route);
}