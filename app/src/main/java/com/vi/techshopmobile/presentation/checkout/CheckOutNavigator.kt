package com.vi.techshopmobile.presentation.checkout

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.ContextThemeWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.presentation.checkout.screens.AddNewAddressScreen
import com.vi.techshopmobile.presentation.checkout.screens.DetailAddressScreen
import com.vi.techshopmobile.presentation.checkout.screens.ListInfoScreen
import com.vi.techshopmobile.presentation.checkout.screens.PaymentErrorScreen
import com.vi.techshopmobile.presentation.checkout.screens.PaymentSuccessScreen
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.order_details.OrderDetailsScreen
import dagger.hilt.android.internal.Contexts
import kotlinx.coroutines.newSingleThreadContext

val LocalSelectedIndex = compositionLocalOf<MutableIntState> {
    error("No LocalSelectedIndex provided")
}

var LocalUrlVnPay = compositionLocalOf<MutableState<String>> {
    error("No LocalUrlVnPay provided")
}

@Composable
fun CheckOutNavigator(navGraphController: NavController) {
    val navController = rememberNavController();
    val selectedAddress = remember {
        mutableIntStateOf(0)
    }
    val urlVnPay = remember {
        mutableStateOf("")
    }

    val browserIntent = Intent(Intent.ACTION_VIEW)
    browserIntent.setData(Uri.parse(urlVnPay.toString()))
    if (urlVnPay.value.isNotEmpty()) {
        LocalUriHandler.current.openUri(urlVnPay.toString())
    }


    CompositionLocalProvider(
        LocalSelectedIndex provides selectedAddress,
        LocalUrlVnPay provides urlVnPay
    ) {
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
                route = Route.PaymentSuccessScreen.route
            ) {
                navController.previousBackStackEntry?.savedStateHandle?.get<String?>("id")
                    ?.let { id ->
                        PaymentSuccessScreen(
                            id = id,
                            navGraphController = navGraphController,
                            onNavigateUp = { navController.navigateUp() },
                            navController = navController
                        )
                    }
            }

            composable(
                route = Route.PaymentErrorScreen.route
            ) {
                        PaymentErrorScreen(
                            onNavigateUp = { navController.navigateUp() },
                            navGraphController = navGraphController,
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

            composable(
                route = Route.DetailAddressScreen.route
            ) {
                navController.previousBackStackEntry?.savedStateHandle?.get<String?>("id")
                    ?.let { id ->
                        DetailAddressScreen(
                            onNavigateUp = { navController.navigateUp() },
                            id = id,
                            navController = navController
                        )
                    }
            }

            composable(route = Route.OderDetailsScreen.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<String?>("id")
                    ?.let { id ->
                        OrderDetailsScreen(
                            onNavigateUp = { navGraphController.navigate(Route.TechShopNavigation.route) },
                            id = id,
                            navController = navController
                        )
                    }
            }
        }
    }
}

fun navigateToDetailAddress(navController: NavController, id: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("id", id)
    navController.navigate(Route.DetailAddressScreen.route);
}

fun navigateToDetailOrder(navController: NavController, id: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("id", id)
    navController.navigate(Route.OderDetailsScreen.route) {
        popUpTo(Route.HomeScreen.route) {
            inclusive = true
        }
    };
}

fun navigateToPaymentSuccess(navController: NavController, id: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("id", id)
    navController.navigate(Route.PaymentSuccessScreen.route) {
        popUpTo(Route.HomeScreen.route) {
            inclusive = true
        }
    };
}

fun navigateToPaymentFail(navController: NavController, id: String) {
    navController.navigate(Route.PaymentErrorScreen.route) {
        popUpTo(Route.HomeScreen.route) {
            inclusive = true
        }
    };
}