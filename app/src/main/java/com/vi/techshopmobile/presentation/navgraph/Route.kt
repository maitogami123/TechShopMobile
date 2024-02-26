package com.vi.techshopmobile.presentation.navgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen: Route(route = "onBoardingScreen")
    object HomeScreen: Route(route = "homeScreen")

    // Comment: Navigation is unique route insist of a list of related screen
    object AppStartNavigation : Route(route = "appStartNavigation")
    object TechShopNavigation : Route(route = "techShopNavigation")
}