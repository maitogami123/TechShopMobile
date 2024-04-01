package com.vi.techshopmobile.presentation.checkout.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.FloatingOnBottomBar
import com.vi.techshopmobile.presentation.navgraph.Route

@Composable
fun PaymentErrorScreen(
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
        },
        bottomBar = {
            Column {
//                FloatingBottomBar(buttonText = "Xác nhận lại đơn hàng",
//                    onButtonClick = {
//                    }
//                )
                FloatingOnBottomBar(buttonText = "Trở về đơn hàng chủ", onButtonClick = {
                    navController.navigateUp()
                })
            }

        }
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 67.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(id = R.drawable.success),
                    contentDescription = null,
                )
                Text(
                    text = "Có lỗi xảy ra :(",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight(700),
                        lineHeight = 37.5.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Đơn hàng của bạn đã gặp phải lỗi trong quá trình xử lý. Vui lòng thử lại!",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}