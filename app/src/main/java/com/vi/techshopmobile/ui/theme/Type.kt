package com.vi.techshopmobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.R

val Roboto = FontFamily(
    fonts = listOf(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_medium, FontWeight.SemiBold),
    )
)

val Typography = Typography(
    headlineMedium = TextStyle(
        fontSize = 18.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp
    ),
    displaySmall = TextStyle(
        fontSize = 16.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
    ),
    displayMedium = TextStyle(
        fontSize = 24.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        lineHeight = 32.sp
    ),
    displayLarge = TextStyle(
        fontSize = 32.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
    )
)