package com.vi.techshopmobile.presentation.order_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.cart.components.RowPriceDelivery
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel

@Composable
fun OrderDetailsScreen(onNavigateUp: () -> Unit) {

    val viewModel: PersonalInfoViewModel = hiltViewModel()
    val token = LocalToken.current
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(PersonalInfoEvent.GetAllEventPersonalInfo(token))
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { onNavigateUp() },
                title = "Chi tiết đơn hàng",
                leftBtnIcon = R.drawable.ic_back_arrow
            ) { }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            Text(text = "List sản phẩm")
//            AsyncImage(
//                model = Constants.BASE_URL + "product/get-file?filePath=" + product.thumbnailUri,
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(110.dp)
//                    .clip(RoundedCornerShape(10.dp)),
//                contentScale = ContentScale.Crop,
//                alignment = Alignment.Center
//            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))
            state.value.userDetail?.accountDetail?.let {
                Column {
                    Text(
                        text = ("Địa chỉ mặc định hiện tại"),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Address(
                        name = it.firstName + " " + it.lastName,
                        phoneNumber = "(+84) " + it.phoneNumber,
                        addressNote = it.detailedAddress,
                        address = it.district + " " + it.city
                    )
                }
            }

            Divider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.SmallPadding)
            ) {
                Text(
                    text = "Chi tiết thanh toán",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600)
                    )
                )
                Column(
                    modifier = Modifier.padding(Dimens.SmallPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    RowPriceDelivery(textLeft = "Tổng tiền hàng", textRight = "Miễn Phí")
                    RowPriceDelivery(textLeft = "Tổng tiền hàng", textRight = "Miễn Phí")
                    RowPriceDelivery(textLeft = "Tổng thanh toán", textRight = "100000000VNĐ")
                }
            }
        }
    }

//    LoadingDialog(isLoading = state.isLoading)
}