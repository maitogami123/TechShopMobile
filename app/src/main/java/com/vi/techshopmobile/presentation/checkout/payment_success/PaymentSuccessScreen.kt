package com.vi.techshopmobile.presentation.checkout.payment_success

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PaymentSuccessScreen(
    navGraphController: NavController,
    onNavigateUp: () -> Unit
) {
    Text(text = "Payment Success Screen")
}