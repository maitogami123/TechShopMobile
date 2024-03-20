package com.vi.techshopmobile.presentation.authenticate.authenticate_navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vi.techshopmobile.presentation.authenticate.forget_password.EnterEmailScreen
import com.vi.techshopmobile.presentation.authenticate.forget_password.EnterNewPasswordScreen
import com.vi.techshopmobile.presentation.authenticate.forget_password.EnterOTPScreen
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.authenticate.sign_in.SignInScreen
import com.vi.techshopmobile.presentation.authenticate.sign_up.SignUpScreen

@Composable
fun AuthenticateNavigator(navGraphController: NavController) {
    val navController = rememberNavController();
    NavHost(
        navController = navController,
        startDestination = Route.SignInScreen.route
    ) {
        composable(
            route = Route.SignInScreen.route
        ) {
            SignInScreen(navGraphController, navController)
        }
        composable(
            route = Route.SignUpScreen.route
        ) {
            SignUpScreen(
                onNavigateUp = { navController.navigateUp() },
                navGraphController = navGraphController
            )
        }
        composable(
            route = Route.ForgetPasswordScreenEmailScreen.route
        ) {
            EnterEmailScreen(
                navController = navController,
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = Route.ForgetPasswordScreenOTPScreen.route + "/{email}",
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                }
            )
        ) {
            EnterOTPScreen(
                email = it.arguments?.getString("email"),
                navController = navController,
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = Route.ForgetPasswordScreenNewPasswordScreen.route + "/{email}" + "/{verificationCode}",
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "verificationCode") {
                    type = NavType.StringType
                },
            )
        ) {
            EnterNewPasswordScreen(
                email = it.arguments?.getString("email"),
                verificationCode = it.arguments?.getString("verificationCode"),
                navController = navController,
                onNavigateUp = { navController.navigateUp() })
        }
    }

}