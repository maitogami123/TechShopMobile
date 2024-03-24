package com.vi.techshopmobile.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.ExtraSmallGap
import com.vi.techshopmobile.presentation.Dimens.IconSizeMedium
import com.vi.techshopmobile.presentation.Dimens.SmallGap
import com.vi.techshopmobile.ui.theme.Gray_200
import com.vi.techshopmobile.ui.theme.Roboto

@Composable
fun Address(
    modifier: Modifier = Modifier,
    name: String,
    phoneNumber: String,
    addressNote: String,
    address: String,
) {
    Column(
        modifier = modifier.drawWithContent {
          drawContent()
            clipRect {
                val strokeWidth = Stroke.HairlineWidth + 8f
                val y = size.height
                drawLine(
                    brush = SolidColor(Color.Black),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Square,
                    start = Offset.Zero,
                    end = Offset.Zero.copy(y = y)
                )
            }
        }.padding(start = ExtraSmallGap),
        verticalArrangement = Arrangement.spacedBy(ExtraSmallGap)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = "User Avatar",
                modifier = Modifier.size(IconSizeMedium)
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = SmallGap)
                    .width(1.dp)
                    .height(12.dp)
                    .background(color = Gray_200)
            )
            Image(
                painter = painterResource(R.drawable.tel12),
                contentDescription = "Phone number"
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = phoneNumber,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Text(
            text = addressNote,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = address,
            style = MaterialTheme.typography.labelMedium
        )
    }
}