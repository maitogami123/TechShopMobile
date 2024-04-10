package com.vi.techshopmobile.presentation.about

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation

@Composable
fun AboutScreen(onNavigateUp: () -> Unit){
    val token = LocalToken.current
    val context = LocalContext.current
    val bullet = "\u2022"
    val member = listOf(
        "Lê Thái Vi",
        "Trịnh Quang Long",
        "Bùi Tuấn Kiệt",
        "Nguyễn Văn Lương",
        "Nguyễn Hoàng Khôi Nguyên",
        "Hà Quốc Vĩ"
    )
    val policy = listOf(
        "Chính sách bảo hành",
        "Chính sách thanh toán",
        "Chính sách giao hàng",
        "Chính sách bảo mật"
    )
    val support = listOf(
        "Hotline hỗ trợ khách hàng: 1900 1001",
        "Email hỗ trợ: support@techshop.com"
    )
    val payment = listOf(
        "Tiền mặt",
        "VNPay",
        "Momo"
    )

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Về TechShop",
                leftBtnIcon = R.drawable.ic_back_arrow
            ) { } 
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                text = ("Về chúng tôi"),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                buildAnnotatedString {
                    member.forEach {
                        withStyle(style = androidx.compose.ui.text.ParagraphStyle()) {
                            append(bullet)
                            append("\t\t")
                            append(it)
                        }
                    }
                },
                modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)
            )
            Divider()

            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),

                text = ("Chính sách"),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                buildAnnotatedString {
                    policy.forEach {
                        withStyle(style = androidx.compose.ui.text.ParagraphStyle()) {
                            append(bullet)
                            append("\t\t")
                            append(it)
                        }
                    }
                },
                modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)
            )
            Divider()

            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                text = ("Tổng đài hỗ trợ (8:00 - 21:00)"),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                buildAnnotatedString {
                    support.forEach {
                        withStyle(style = androidx.compose.ui.text.ParagraphStyle()) {
                            append(bullet)
                            append("\t\t")
                            append(it)
                        }
                    }
                },
                modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)
            )
            Divider()

            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                text = ("Các phương thức thanh toán"),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                buildAnnotatedString {
                    payment.forEach {
                        withStyle(style = androidx.compose.ui.text.ParagraphStyle()) {
                            append(bullet)
                            append("\t\t")
                            append(it)
                        }
                    }
                },
                modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)
            )
            Divider()

            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                text = ("Kết nối với chúng tôi"),
                style = MaterialTheme.typography.headlineMedium
            )
            Row(modifier = Modifier.padding(start = 16.dp)) {
                IconButton(onClick = {
                    val facebookIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
                    context.startActivity(facebookIntent)
                }) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook"
                    )
                }
                IconButton(onClick = {
                    val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/maitogami123/TechShopMobile"))
                    context.startActivity(githubIntent) }) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = "Github"
                    )
                }
            }
        }
    }
}