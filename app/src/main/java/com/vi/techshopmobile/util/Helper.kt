package com.vi.techshopmobile.util

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.google.gson.Gson
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
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(0)
    format.currency = Currency.getInstance("VND")
    val formatedStr = format.format(price).replace(",",".")
    return formatedStr.substring(1, formatedStr.length) + " VNĐ"
}

fun decodeToken(token: String): UserToken {
    if (token.isBlank())
        return UserToken();
    val chunks: List<String> = token.split(".")
    val decoder: Base64.Decoder = Base64.getUrlDecoder()
    val gson = Gson()
    return gson.fromJson(String(decoder.decode(chunks[1])), UserToken::class.java)

}

fun connectInputtedCode(
    textList: List<MutableState<TextFieldValue>>,
    onController: (() -> Unit)? = null
) : String {
    var code = ""
    for (text in textList) {
        code += text.value.text
    }
    return code;
}

fun convertMilisToMinus(millis: Long): String{
    return String.format("%02d : %02d ",
        TimeUnit.MILLISECONDS.toMinutes(millis),
        TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
    );
}