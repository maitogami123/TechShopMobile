package com.vi.techshopmobile.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arrow.core.Either
import com.vi.techshopmobile.R
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

@Composable
fun RowPaymentNavigate(
    textLeft: String,
    textRight: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textLeft, style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 16.sp
            )
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 12.dp),
                text = textRight, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null
            )
        }
    }
}

@Composable
fun RowPaymentNavigate(textLeft: String, textRight: String, modifier: Modifier = Modifier) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textLeft, style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 16.sp
            )
        )

        Text(
            text = textRight, style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(400),
                fontSize = 14.sp
            )
        )

    }
}

@Composable
fun RowPaymentGateNavigate(
    textLeft: String,
    textRight: String,
    headIconGate: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textLeft, style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 16.sp
            )
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = headIconGate,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = textRight, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null
            )
        }

    }
}

@Composable
fun RowPaymentGateNavigate(
    textLeft: String,
    textRight: String,
    headIconGate: Painter,
    modifier: Modifier = Modifier,
) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textLeft, style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 16.sp
            )
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = headIconGate,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = textRight, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                )
            )
        }
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
            RowPaymentNavigate(
                textLeft = "Phương thức thanh toán",
                textRight = "Thanh toán online"
            ) {}
            RowPaymentNavigate(
                textLeft = "Phương thức thanh toán",
                textRight = "Thanh toán online"
            )
            RowPaymentGateNavigate(
                textLeft = "Cổng thanh toán", textRight = "VN PAY", painterResource(
                    id = R.drawable.ic_bell
                )
            ) {}
            RowPaymentGateNavigate(
                textLeft = "Cổng thanh toán", textRight = "VN PAY", painterResource(
                    id = R.drawable.ic_bell
                )
            )
        }
    }

}