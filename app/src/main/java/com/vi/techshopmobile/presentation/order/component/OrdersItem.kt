package com.vi.techshopmobile.presentation.order.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.domain.model.OrderItem

@Composable
fun OrdersItem(order: OrderItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID: ${order.id}")
            Text(text = "Username: ${order.username}")
            Text(text = "Status: ${order.status}")
            Text(text = "Total: ${order.total}")
            // Thêm các thông tin khác của đơn hàng tại đây nếu cần
        }
    }
}
