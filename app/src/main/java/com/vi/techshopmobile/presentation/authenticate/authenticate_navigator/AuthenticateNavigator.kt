package com.vi.techshopmobile.presentation.authenticate.authenticate_navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vi.techshopmobile.presentation.user.forget_password.EnterEmailScreen
import com.vi.techshopmobile.presentation.user.forget_password.EnterNewPasswordScreen
import com.vi.techshopmobile.presentation.user.forget_password.EnterOTPScreen
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.authenticate.sign_in.SignInScreen
import com.vi.techshopmobile.presentation.authenticate.sign_up.SignUpScreen
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoScreen

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
                onNavigateUp = { navController.navigate(Route.SignInScreen.route) },
                navGraphController = navGraphController
            )
        }
        composable(
            route = Route.ForgetPasswordScreenEmailScreen.route
        ) {
            EnterEmailScreen(
                navController = navController,
                onNavigateUp = { navController.navigate(Route.SignUpScreen.route)})
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
                onNavigateUp = { navController.navigate(Route.ForgetPasswordScreenEmailScreen.route) })
        }
        composable(
            route = Route.ForgetPasswordScreenNewPasswordScreen.route + "/{email}" + "/{otpValue}",
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "otpValue") {
                    type = NavType.StringType
                },
            )
        ) {
            EnterNewPasswordScreen(
                email = it.arguments?.getString("email"),
                verificationCode = it.arguments?.getString("otpValue"),
                navController = navController,
                onNavigateUp = { navController.navigate(Route.ForgetPasswordScreenOTPScreen.route) })
        }
    }

}