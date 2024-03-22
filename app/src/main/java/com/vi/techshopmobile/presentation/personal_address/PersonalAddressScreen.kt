package com.vi.techshopmobile.presentation.personal_address

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.common.Address
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.wish_list.WishListViewModel

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun PersonalAddressScreen(){
    val viewModel: WishListViewModel = hiltViewModel()
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
//                .padding(start = 16.dp, top = 12.dp, end = 138.dp, bottom = 12.dp)
            ) {
                UtilityTopNavigation(
                    onRightBtnClick = {

                    },
                    leftBtnIcon = R.drawable.ic_left_arrow,
                ) {}

                Text(

                    text = "Dia chi",
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
    ){
        Column(modifier = Modifier.fillMaxWidth()) {

        }
    }

}