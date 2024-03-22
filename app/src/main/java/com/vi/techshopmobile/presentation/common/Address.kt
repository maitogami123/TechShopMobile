package com.vi.techshopmobile.presentation.common

import android.location.Address
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.R

@Composable
fun Address(
    name: String,
    phoneNumber: String,
    addressNote: String,
    address: String,
)
{
    var isExpanded by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = "User Avatar"
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = name,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF383838),
                    )
            )
            Spacer(modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .width(1.dp)
                .height(12.dp)
                .background(color = Color(0xFFADADAD))

            )
            Image(
                painter = painterResource(R.drawable.tel12),
                contentDescription = "User Avatar"
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = phoneNumber,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF383838),
                    )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = addressNote,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF383838),

                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = address,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF383838),

                )

        )
    }
}

