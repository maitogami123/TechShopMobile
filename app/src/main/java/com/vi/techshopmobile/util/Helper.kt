package com.vi.techshopmobile.util

import androidx.navigation.NavController
import java.text.NumberFormat
import java.util.Currency

fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

fun formatPrice(price: Double): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(0)
    format.currency = Currency.getInstance("VND")
    val formatedStr = format.format(price).replace(",",".")
    return formatedStr.substring(1, formatedStr.length) + " VNĐ"
}