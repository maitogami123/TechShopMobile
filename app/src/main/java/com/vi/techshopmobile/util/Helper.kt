package com.vi.techshopmobile.util

import android.content.res.AssetManager
import androidx.navigation.NavController
import com.google.gson.Gson
import com.vi.techshopmobile.domain.model.Provinces
import com.vi.techshopmobile.domain.model.UserToken
import java.text.NumberFormat
import java.util.Base64
import java.util.Currency
import java.util.concurrent.TimeUnit


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
    val formatter = NumberFormat.getCurrencyInstance()
    formatter.maximumFractionDigits = 2
    formatter.currency = Currency.getInstance("VND")
    val formattedPrice = formatter.format(price)
    return formattedPrice.replace("₫", "").replaceFirst(",", ".") + "VNĐ"
}
fun decodeToken(token: String): UserToken {
    if (token.isBlank())
        return UserToken();
    val chunks: List<String> = token.split(".")
    val decoder: Base64.Decoder = Base64.getUrlDecoder()
    val gson = Gson()
    return gson.fromJson(String(decoder.decode(chunks[1])), UserToken::class.java)

}

fun convertMilisToMinus(millis: Long): String {
    return String.format(
        "%02d : %02d ",
        TimeUnit.MILLISECONDS.toMinutes(millis),
        TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
    );
}

fun formatPhoneNumber(phoneNumber: String): String {
    if (phoneNumber.length < 11)
        return phoneNumber
    return String.format(
        "+84 %s-%s-%s",
        phoneNumber.substring(1, 4),
        phoneNumber.substring(4, 7),
        phoneNumber.substring(7)
    )
}