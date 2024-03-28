package com.vi.techshopmobile.presentation.checkout.checkout_navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.presentation.authenticate.sign_in.SignInScreen
import com.vi.techshopmobile.presentation.authenticate.sign_up.SignUpScreen
import com.vi.techshopmobile.presentation.checkout.CheckOutScreen
import com.vi.techshopmobile.presentation.checkout.checkout_done.CheckOutDoneScreen
import com.vi.techshopmobile.presentation.checkout.list_information.ListInfoScreen
import com.vi.techshopmobile.presentation.checkout.paymen_option.PaymentOptionScreen
import com.vi.techshopmobile.presentation.checkout.payment_error.PaymentErrorScreen
import com.vi.techshopmobile.presentation.checkout.payment_success.PaymentSuccessScreen
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