package com.vi.techshopmobile.presentation.common

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.Dimens.ExtraSmallGap
import com.vi.techshopmobile.presentation.Dimens.IconSizeMedium
import com.vi.techshopmobile.presentation.Dimens.SmallGap
import com.vi.techshopmobile.ui.theme.Gray_200
import com.vi.techshopmobile.ui.theme.Roboto
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
/**
 * A Address contain info about user's address.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param name to show user's name
 * @param phoneNumber to show phonenumber
 * @param addressNote to show detail address
 * @param address to address sumary
 * @param isDefault to show address, is it, isn't?
 */
@Composable
fun Address(
    modifier: Modifier = Modifier,
    name: String,
    phoneNumber: String,
    addressNote: String,
    address: String,
    isDefault: Boolean? = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawWithContent {
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
            }
            .padding(start = ExtraSmallGap, end = 0.dp, top = 4.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(ExtraSmallGap),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700)
                )
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
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = addressNote,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = address,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        )
        if (isDefault == true) {
            Box(
                modifier = Modifier.border(
                    color = Color(0xffFFBB32),
                    width = 1.dp
                ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = "Mặc định",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xffFFBB32)
                    )
                )
            }
        }
    }
}

/**
 * A Address contain info about user's address.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param name to show user's name
 * @param phoneNumber to show phonenumber
 * @param addressNote to show detail address
 * @param address to address sumary
 * @param isDefault to show address, is it, isn't?
 * @param onEdit to use edit address
 */
@Composable
fun Address(
    modifier: Modifier = Modifier,
    name: String,
    phoneNumber: String,
    addressNote: String,
    address: String,
    isDefault: Boolean = false,
    onEdit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawWithContent {
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
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = ExtraSmallGap, end = ExtraSmallGap, top = 4.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(ExtraSmallGap)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(700)
                    )
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
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    )
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = addressNote,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = address,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
            if (isDefault == true) {
                Box(
                    modifier = Modifier.border(
                        color = Color(0xffFFBB32),
                        width = 1.dp
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = "Mặc định",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xffFFBB32)
                        )
                    )
                }
            }
        }

        Text(
            modifier = Modifier
                .weight(.1f)
                .align(Alignment.Top)
                .padding(top = 4.dp)
                .clickable { onEdit() },
            text = "Sửa",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xff3F83F8)
            )
        )
    }
}

/**
 * A Address contain info about user's address.
 *
 * @param modifier the icon to be displayed. If no value passed, it will not display anything
 * @param name to show user's name
 * @param phoneNumber to show phonenumber
 * @param addressNote to show detail address
 * @param address to address sumary
 * @param isDefault to show address, is it, isn't?
 * @param onEdit to use edit address
 * @param isSelected To check if the address is selected or not
 */
@Composable
fun AddressSelected(
    modifier: Modifier = Modifier,
    name: String,
    phoneNumber: String,
    addressNote: String,
    address: String,
    isDefault: Boolean = false,
    onEdit: () -> Unit,
    isSelected: Boolean = false
) {
    val colors = RadioButtonDefaults.colors(
        selectedColor = Color(0xff3F83F8),
        unselectedColor = Color(0xff8A8A8A),
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(ExtraSmallGap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(colors = colors, selected = isSelected, onClick = { /*TODO*/ })
        Column(
            modifier = Modifier
                .drawWithContent {
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
                }
                .weight(1f)
                .padding(start = ExtraSmallGap, end = ExtraSmallGap, top = 4.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(ExtraSmallGap)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(700)
                    )
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
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    )
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = addressNote,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = address,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
            if (isDefault) {
                Box(
                    modifier = Modifier.border(
                        color = Color(0xffFFBB32),
                        width = 1.dp
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = "Mặc định",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xffFFBB32)
                        )
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .weight(.1f)
                .align(Alignment.Top)
                .padding(top = 4.dp)
                .clickable { onEdit() },
            text = "Sửa",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xff3F83F8)
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddressPreview() {
    val radioButtons = listOf(0, 1, 2)
    val (selectedButton, onOptionSelected) = remember {
        mutableStateOf(radioButtons[0])
    }
    TechShopMobileTheme {
        Column(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Address(
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức"
            )

            Divider()

            Address(
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức",
                isDefault = true
            )

            Divider()

            Address(
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức",
                onEdit = {}
            )

            Divider()

            Address(
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức",
                isDefault = true,
                onEdit = {}
            )
            Divider()

            AddressSelected(
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức",
                isDefault = false,
                isSelected = false,
                onEdit = {},
            )

            Divider()
            AddressSelected(
                name = "Lê Thái Vi",
                phoneNumber = "(+84) 929358925",
                addressNote = "123/456/789 Đường số 10",
                address = "phường Hiệp Bình Phước, thành phố Thủ Đức",
                isDefault = true,
                isSelected = true,
                onEdit = {},
            )
        }
    }
}
