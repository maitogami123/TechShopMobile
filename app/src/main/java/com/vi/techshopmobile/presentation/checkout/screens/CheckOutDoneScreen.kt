package com.vi.techshopmobile.presentation.checkout.screens

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