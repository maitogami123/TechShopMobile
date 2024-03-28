package com.vi.techshopmobile.presentation.personal_address

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.common.CustomButton
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewModel

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun PersonalAddressScreen(onNavigateUp: () -> Unit){
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
                title = "Địa chỉ nhận hàng", leftBtnIcon = R.drawable.ic_back_arrow
            ) { }

        }
    ){
        val topPadding = it.calculateTopPadding()

        Column( modifier = Modifier
            .padding(top = topPadding + Dimens.SmallPadding)
            .padding(horizontal = Dimens.SmallPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding)
        ) {
            state.value.userDetail?.accountDetail?.let {
              Box(  modifier = Modifier.fillMaxWidth()
              ){
                  Address(
                      name = it.firstName + " "+  it.lastName,
                      phoneNumber = "(+84 )" + it.phoneNumber,
                      addressNote = it.detailedAddress,
                      address = it.district+ " " + it.city
                  )
                  Text(
                      text = "Sửa",
                      style = TextStyle(
                          fontSize = 14.sp,
                          lineHeight = 18.sp,
                          fontFamily = FontFamily(Font(R.font.roboto_medium)),
                          fontWeight = FontWeight(400),
                          color = Color(0xFF3F83F8),
                      ),
                      modifier = Modifier.align(Alignment.TopEnd)
                  )            }

              }
                Text(
                    text = "Mặc định",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFBB32),

                        ),
                )
                Divider()
            state.value.userDetail?.accountDetail?.let {
                Box(  modifier = Modifier.fillMaxWidth()
                ){
                    Address(
                        name = it.firstName + " "+  it.lastName,
                        phoneNumber = "(+84 )" + it.phoneNumber,
                        addressNote = it.detailedAddress,
                        address = it.district+ " " + it.city
                    )
                    Text(
                        text = "Sửa",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3F83F8),
                        ),
                        modifier = Modifier.align(Alignment.TopEnd)
                    )            }

            }
            Divider()
            state.value.userDetail?.accountDetail?.let {
                Box(  modifier = Modifier.fillMaxWidth()
                ){
                    Address(
                        name = it.firstName + " "+  it.lastName,
                        phoneNumber = "(+84 )" + it.phoneNumber,
                        addressNote = it.detailedAddress,
                        address = it.district+ " " + it.city
                    )
                    Text(
                        text = "Sửa",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3F83F8),
                        ),
                        modifier = Modifier.align(Alignment.TopEnd)
                    )            }

            }

            Spacer(modifier = Modifier.weight(1f))
            CustomButton(modifier = Modifier.fillMaxWidth(), text = "+ Thêm địa chỉ mới") {}

        }

        }

}
