package com.vi.techshopmobile.presentation.order.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.domain.model.OrderItem

@Composable
fun OrdersItem(order: OrderItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = cardElevation(8.dp),
        colors = cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .weight(3f),
                    textAlign = TextAlign.Start,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    text = "Mã đơn hàng: #${order.id}"
                )
                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF4682B4),
                            shape = RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.Center

                    ) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = TextUnit.Unspecified),
                        color = Color(0xFF4682B4),
                        text = "${order.status}"
                    )
                }

            }
            Text(
                modifier = Modifier.padding(3.dp),
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W400),
                text = "Ngày đặt hàng: ${order.createdAt}"
            )
            Divider()
            Row {
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .weight(1f),
                    text = "Thành tiền:"
                )
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    color = Color(0xFFFF2400), text = "${order.total} VNĐ"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

//            Text(text = "Products:")
//            order.products.forEach { product ->
//                Text(text = " - ${product.name}: ${product.quantity} x ${product.price}")
        }
    }
}