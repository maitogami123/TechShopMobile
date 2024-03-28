package com.vi.techshopmobile.presentation.checkout.payment_error

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PaymentErrorScreen(
    navGraphController: NavController,
    onNavigateUp: () -> Unit
) {
    Text(text = "Payment Error")
}