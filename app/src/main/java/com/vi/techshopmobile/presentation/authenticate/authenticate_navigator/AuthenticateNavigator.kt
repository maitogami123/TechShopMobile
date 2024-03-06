package com.vi.techshopmobile.presentation.authenticate.authenticate_navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            SignUpScreen(onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = Route.ForgetPasswordScreenEmailScreen.route
        ) {
            EnterEmailScreen(navController = navController, onNavigateUp =  { navController.navigateUp() })
        }
        composable(
            route = Route.ForgetPasswordScreenOTPScreen.route
        ) {
            EnterOTPScreen(navController = navController, onNavigateUp =  { navController.navigateUp() })
        }
        composable(
            route = Route.ForgetPasswordScreenNewPasswordScreen.route
        ) {
            EnterNewPasswordScreen(navController = navController, onNavigateUp =  { navController.navigateUp() })
        }
    }

}