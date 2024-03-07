package com.vi.techshopmobile.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.presentation.authenticate.authenticate_navigator.AuthenticateNavigator
import com.vi.techshopmobile.presentation.home_navigator.HomeNavigator
import com.vi.techshopmobile.presentation.home_navigator.TechShopNavigatorViewModel
import com.vi.techshopmobile.presentation.onboarding.OnBoardingScreen
import com.vi.techshopmobile.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()
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