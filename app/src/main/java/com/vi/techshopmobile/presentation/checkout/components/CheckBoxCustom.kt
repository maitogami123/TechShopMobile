package com.vi.techshopmobile.presentation.checkout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun PaymentOption(
    modifier: Modifier = Modifier,
    title: String,
    headIcon: Painter,
    isChecked: Boolean = false,
    onClick: () -> Unit
) {
    val colors = CheckboxDefaults.colors(
        checkedColor = Color(0xff3F83F8),
        uncheckedColor = Color(0xff8A8A8A),
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = headIcon,
            contentDescription = null,
            modifier = Modifier.padding(start = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .drawWithContent {
                    drawContent()
                    clipRect {
                        val strokeWidth = Stroke.HairlineWidth + 8f
                        val x = size.width
                        drawLine(
                            brush = SolidColor(Color.Black),
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Square,
                            start = Offset.Zero.copy(y = size.height),
                            end = Offset.Zero.copy(x = x, y = size.height)
                        )
                    }
                },
        ) {
            Text(
                text = title, style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
            )
            Checkbox(
                colors = colors,
                checked = isChecked,
                onCheckedChange = {
                    onClick()
                },
            )
        }

    }
}

@Composable
fun CheckBoxText(
    modifier: Modifier = Modifier,
    title: String,
    isChecked: Boolean = false,
    onClick: () -> Unit
) {
    val colors = CheckboxDefaults.colors(
        checkedColor = Color(0xff3F83F8),
        uncheckedColor = Color(0xff8A8A8A),
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier= Modifier.padding(start = Dimens.SmallPadding),
            text = title, style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        )
        Checkbox(
            colors = colors,
            checked = isChecked,
            onCheckedChange = {
                onClick()
            },
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PaymentOptionPreview() {
    TechShopMobileTheme {
        Column(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PaymentOption(
                title = "Chuyển Khoản",
                headIcon = painterResource(id = R.drawable.ic_cast),
                isChecked = false
            ) {}
            PaymentOption(
                title = "Thanh toán khi nhận hàng",
                headIcon = painterResource(id = R.drawable.ic_money),
                isChecked = true
            ) {}
            PaymentOption(
                title = "VN Pay",
                headIcon = painterResource(id = R.drawable.ic_cast),
                isChecked = false
            ) {}
            PaymentOption(
                title = "Momo",
                headIcon = painterResource(id = R.drawable.ic_money),
                isChecked = true
            ) {}
            CheckBoxText(
                title = "Đặt làm địa chỉ mặc định",
                isChecked = true
            ) {}
        }
    }
}