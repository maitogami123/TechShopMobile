package com.vi.techshopmobile.presentation.checkout

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.presentation.checkout.screens.CheckOutDoneScreen
import com.vi.techshopmobile.presentation.checkout.screens.ListInfoScreen
import com.vi.techshopmobile.presentation.checkout.screens.PaymentErrorScreen
import com.vi.techshopmobile.presentation.checkout.screens.PaymentOptionScreen
import com.vi.techshopmobile.presentation.checkout.screens.PaymentSuccessScreen
import com.vi.techshopmobile.presentation.navgraph.Route

@Composable
fun CheckOutNavigator(navGraphController: NavController) {
    val navController = rememberNavController();
    NavHost(
        navController = navController,
        startDestination = Route.CheckOutScreen.route
    ) {
        composable(
            route = Route.CheckOutScreen.route
        ) {
            CheckOutScreen(
                navGraphController = navGraphController,
                navController = navController,
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = Route.CheckOutDoneScreen.route
        ) {
            CheckOutDoneScreen(
                onNavigateUp = { navController.navigateUp() },
                navGraphController = navGraphController
            )
        }
        composable(
            route = Route.ListInfoScreen.route
        ) {
            ListInfoScreen(
                onNavigateUp = { navController.navigateUp() },
                navGraphController = navGraphController
            )
        }
        composable(
            route = Route.PaymentOptionScreen.route
        ) {
            PaymentOptionScreen(
                onNavigateUp = { navController.navigateUp() },
                navGraphController = navGraphController
            )
        }
        composable(
            route = Route.PaymentSuccessnScreen.route
        ) {
            PaymentSuccessScreen(
                onNavigateUp = { navController.navigateUp() },
                navGraphController = navGraphController
            )
        }
        composable(
            route = Route.PaymentErrorScreen.route
        ) {
            PaymentErrorScreen(
                onNavigateUp = { navController.navigateUp() },
                navGraphController = navGraphController
            )
        }
    }
}