package com.vi.techshopmobile.presentation.order.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.domain.model.OrderItem
import com.vi.techshopmobile.util.formatPrice

@Composable
fun OrdersItem(order: OrderItem, onClick: () -> Unit) {
    val color = if (order.status == "CANCELED") Color(0xffFF3A28)
    else if (order.status == "SUCCESS") Color(0xff53B175)
    else if (order.status == "CONFIRMED") Color(0xff3FA4FC)
    else if (order.status == "PENDING") Color(0xffFFBB32)
    else if (order.status == "DELIVERING") Color(0xff1e91cf)
    else Color(0xffe3d4d4)

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
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600)
                    ),
                    text = "Mã đơn hàng: #${order.id}"
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = color,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400)
                        ),
                        color = color,
                        text = order.status
                    )
                }

            }
            Text(
                modifier = Modifier.padding(3.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400)
                ),
                text = "Ngày đặt hàng: ${order.createdAt}"
            )
            Divider()
            Row {
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .weight(1f),
                    text = "Thành tiền:",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    color = Color(0xFFFF2400), text = formatPrice(order.total),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    )
                )
            }
        }
    }
}