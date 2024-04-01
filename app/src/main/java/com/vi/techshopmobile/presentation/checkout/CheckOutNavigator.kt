package com.vi.techshopmobile.presentation.checkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.presentation.checkout.screens.AddNewAddressScreen
import com.vi.techshopmobile.presentation.checkout.screens.ListInfoScreen
import com.vi.techshopmobile.presentation.checkout.screens.PaymentErrorScreen
import com.vi.techshopmobile.presentation.checkout.screens.PaymentSuccessScreen
import com.vi.techshopmobile.presentation.navgraph.Route

val LocalSelectedIndex = compositionLocalOf<MutableIntState> {
    error("No LocalSelectedIndex provided")
}

@Composable
fun CheckOutNavigator(navGraphController: NavController) {
    val navController = rememberNavController();
    val selectedAddress = remember {
        mutableIntStateOf(0)
    }
    
    CompositionLocalProvider(LocalSelectedIndex provides selectedAddress) {
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
                    onNavigateUp = {
                        navGraphController.navigate(Route.TechShopNavigation.route)
                    }
                )
            }
            composable(
                route = Route.ListInfoScreen.route
            ) {
                ListInfoScreen(
                    onNavigateUp = { navController.navigateUp() },
                    navController = navController
                )
            }
            composable(
                route = Route.PaymentSuccessnScreen.route
            ) {
                PaymentSuccessScreen(
                    navGraphController= navGraphController,
                    onNavigateUp = { navController.navigateUp() },
                    navController = navController
                )
            }
            composable(
                route = Route.PaymentErrorScreen.route
            ) {
                PaymentErrorScreen(
                    onNavigateUp = { navController.navigateUp() },
                    navController = navController
                )
            }
            composable(
                route = Route.AddNewAddressScreen.route
            ) {
                AddNewAddressScreen(
                    onNavigateUp = { navController.navigateUp() },
                    navController = navController
                )
            }
        }
    }
}