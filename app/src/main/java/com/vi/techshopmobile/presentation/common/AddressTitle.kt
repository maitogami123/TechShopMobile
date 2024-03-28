package com.vi.techshopmobile.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.ui.theme.Gray_200
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

/**
 * A AddressTitle contain info about user's address.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param name to show user's name
 * @param phoneNumber to show phonenumber
 * @param addressNote to show detail address
 * @param address to address sumary
 * @param title to show title of information
 * @param onEdit to use edit address
 */
@Composable
fun AddressTitle(
    modifier: Modifier = Modifier,
    title: String,
    name: String,
    phoneNumber: String,
    addressNote: String,
    address: String,
    onEdit: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = Dimens.SmallGap),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp
                )
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(top = 4.dp)
                    .clickable { onEdit() },
                text = "Thay đổi",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xff3F83F8)
                )
            )
        }
        Address(
            name = name,
            phoneNumber = phoneNumber,
            addressNote = addressNote,
            address = address
        )
    }
}

/**
 * A AddressTitle contain info about user's address.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param name to show user's name
 * @param phoneNumber to show phonenumber
 * @param addressNote to show detail address
 * @param address to address sumary
 * @param title to show title of information
 */
@Composable
fun AddressTitle(
    modifier: Modifier = Modifier,
    title: String,
    name: String,
    phoneNumber: String,
    addressNote: String,
    address: String,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = Dimens.SmallGap),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp
                )
            )
        }
        Address(
            name = name,
            phoneNumber = phoneNumber,
            addressNote = addressNote,
            address = address
        )
    }
}
@Preview(showSystemUi = true)
@Composable
fun AddressTitlePreview() {
    TechShopMobileTheme {
        Column(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddressTitle(
                title = "Địa chỉ nhận hàng",
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức",
                onEdit = {}
            )

            AddressTitle(
                title = "Thông tin cá nhân",
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức",
            )
        }
    }

}