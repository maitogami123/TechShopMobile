package com.vi.techshopmobile.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vi.techshopmobile.R

@Composable
fun LoadingDialog(isLoading: Boolean) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isLoading
    )
//    if (isLoading) {
//        Dialog(
//            onDismissRequest = { /*TODO*/ },
//            properties = DialogProperties(
//                dismissOnClickOutside = false
//            )
//        ) {
//            Box(
//                modifier = Modifier
//                    .width(200.dp)
//                    .clip(RoundedCornerShape(15.dp))
//                    .background(Color.White),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator(modifier = Modifier.padding(10.dp)) //màn hình loading
//            }
//        }
//    }
    if (isLoading) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            progress = { progress })
    }
}