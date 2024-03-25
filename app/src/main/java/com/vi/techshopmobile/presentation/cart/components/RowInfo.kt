package com.vi.techshopmobile.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arrow.core.Either
import com.vi.techshopmobile.data.remote.cart.CartResponse
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.formatPrice

@Composable
fun RowPrice(textLeft: String, textRight: Double, modifier: Modifier = Modifier) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textLeft, style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp
            )
        )
        Text(
            text = formatPrice(textRight), style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun RowPriceDelivery(textLeft: String, textRight: String, modifier: Modifier = Modifier) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textLeft, style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp
            )
        )
        Text(
            text = textRight, style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun RowTotalPrice(textLeft: String, textRight: Double, modifier: Modifier = Modifier) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textLeft, style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 18.sp
            )
        )
        Text(
            text = formatPrice(textRight), style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(600),
                color = Color(0xffFF3A28),
                fontSize = 18.sp
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun RowPricePreview() {
    TechShopMobileTheme {
        Column(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RowPrice(textLeft = "Tổng tiền hàng", textRight = 12312314.000)
            RowPriceDelivery(textLeft = "Tổng tiền hàng", textRight = "Miễn Phí")
            RowTotalPrice(textLeft = "Tổng thanh toán", textRight = 2.129e+07)
        }
    }

}