package com.vi.techshopmobile.presentation.checkout.checkout_done

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CheckOutDoneScreen(
    navGraphController: NavController,
    onNavigateUp: () -> Unit
) {
    Text(text = "CheckOutDoneScreen")
}