package com.vi.techshopmobile.presentation.order_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.cart.components.RowPrice
import com.vi.techshopmobile.presentation.cart.components.RowPriceDelivery
import com.vi.techshopmobile.presentation.cart.components.RowTotalPrice
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToDetails
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.order.OrdersViewModel
import com.vi.techshopmobile.presentation.order.component.OrdersItem
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel
import com.vi.techshopmobile.ui.theme.Blue_500
import com.vi.techshopmobile.util.Constants

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