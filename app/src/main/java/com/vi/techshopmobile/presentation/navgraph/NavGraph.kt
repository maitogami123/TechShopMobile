package com.vi.techshopmobile.presentation.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.presentation.authenticate.authenticate_navigator.AuthenticateNavigator
import com.vi.techshopmobile.presentation.cart.CartScreen
import com.vi.techshopmobile.presentation.chatAI.ChatAiScreen
import com.vi.techshopmobile.presentation.home.home_navigator.HomeNavigator
import com.vi.techshopmobile.presentation.home.home_navigator.TechShopNavigatorViewModel
import com.vi.techshopmobile.presentation.onboarding.OnBoardingScreen
import com.vi.techshopmobile.presentation.onboarding.OnBoardingViewModel
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import com.vi.techshopmobile.util.decodeToken

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()
    val currentTime = System.currentTimeMillis()
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(viewModel::onEvent)
            }
        }

        navigation(
            route = Route.TechShopNavigation.route,
            startDestination = Route.HomeScreen.route,
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                HomeNavigator(navController)
            }
        }

        navigation(
            route = Route.AuthenticateNavigation.route,
            startDestination = Route.SignInScreen.route
        ) {
            composable(route = Route.SignInScreen.route) {
                AuthenticateNavigator(navController)
            }
        }
    }

}