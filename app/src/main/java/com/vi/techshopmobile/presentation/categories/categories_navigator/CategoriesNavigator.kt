package com.vi.techshopmobile.presentation.categories.categories_navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.presentation.navgraph.Route

@Composable
fun CategoriesNavigator(navGraphController: NavController) {
    val navController = rememberNavController();
    NavHost(
        navController = navController,
        startDestination = Route.SignInScreen.route
    ) {

    }
}