package com.vi.techshopmobile.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.presentation.authenticate.authenticate_navigator.AuthenticateNavigator
import com.vi.techshopmobile.presentation.checkout.CheckOutNavigator
import com.vi.techshopmobile.presentation.home.home_navigator.HomeNavigator
import com.vi.techshopmobile.presentation.onboarding.OnBoardingScreen
import com.vi.techshopmobile.presentation.onboarding.OnBoardingViewModel

val LocalNavGraphController = compositionLocalOf<NavController> {
    error("No LocalNavController provided")
}

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavGraphController provides navController) {
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

            navigation(
                route = Route.CheckOutScreenNavigation.route,
                startDestination = Route.CheckOutScreen.route
            ) {
                composable(route = Route.CheckOutScreen.route) {
                   CheckOutNavigator(navController)
                }
            }
        }
    }

}