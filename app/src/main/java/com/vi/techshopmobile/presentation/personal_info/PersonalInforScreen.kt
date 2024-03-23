package com.vi.techshopmobile.presentation.personal_info
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.common.Input
import com.vi.techshopmobile.util.decodeToken
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PersonalInfoScreen() {
    val viewModel: PersonalInfoViewModel = hiltViewModel()
    val token = LocalToken.current
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(PersonalInfoEvent.GetAllEventPersonalInfo(token))
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 6.dp,
                        spotColor = Color(0x26000000),
                        ambientColor = Color(0x26000000)
                    )
                    .shadow(
                        elevation = 2.dp,
                        spotColor = Color(0x4D000000),
                        ambientColor = Color(0x4D000000)
                    )
                    .width(412.dp)
                    .height(56.dp)
                    .background(color = Color(0xFFFFFFFF))
            ) {
                UtilityTopNavigation(
                    onRightBtnClick = {

                    },
                    leftBtnIcon = R.drawable.ic_left_arrow,
                ) {}

                Text(

                    text = "Thông tin cá nhân",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF212121),
                        textAlign = TextAlign.Center,
                    )
                )

            }

        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 50.dp)
        ) {
                state.value.userDetail?.accountDetail?.let { it1 ->
                    Input(
                        inputText = it1.firstName ,
                        labelText = "Họ và tên lót",
                        modifier = Modifier.fillMaxWidth()
                    ) {
            //                       username = it
                    }
                }
            Spacer(modifier = Modifier.height(Dimens.SmallGap))
            state.value.userDetail?.accountDetail?.let { it1 ->
                Input(
                    inputText = it1.lastName,
                    labelText = "Tên",
                    modifier = Modifier.fillMaxWidth()
                ) {
        //                username = it
                }
            }
            Spacer(modifier = Modifier.height(Dimens.SmallGap))
Box(modifier = Modifier.fillMaxWidth()){
    Text(text = "Gmail",
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight(600),
            color = Color(0xFF212121),
            ),
        modifier = Modifier
            .align(Alignment.TopStart)

    )

    Text(text = "Đổi gmail",
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight(400),
            color = Color(0xFF3F83F8),
            textDecoration = TextDecoration.Underline,
        ),
        modifier = Modifier
            .align(Alignment.TopEnd)
        )
}

            state.value.userDetail?.let { it1 ->
                Input(
                    inputText = it1.email,
                    modifier = Modifier.fillMaxWidth()
                ) {
        //                gmail = it
                }
            }

            Spacer(modifier = Modifier.height(Dimens.SmallGap))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(380.dp)
                    .height(38.dp)

            ) {
                Text(
                    text = "Đổi mật khẩu",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )

            }


            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(Dimens.SmallGap))
            Text(text = ("Đia chỉ mặc định hiện tại"),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),

                    ))
            state.value.userDetail?.accountDetail?.let { it1 ->
                Address(
                    name = it1.firstName + " " + it1.lastName,
                    phoneNumber = "(+84)" + it1.phoneNumber,
                    addressNote =it1.detailedAddress,
                    address = it1.district + " " + it1.city
                )
            }


        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .padding(top = 800.dp)
                    .width(380.dp)
                    .height(64.dp)
            ) {
                Text(
                    text = "Hoàn tất",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }


}



