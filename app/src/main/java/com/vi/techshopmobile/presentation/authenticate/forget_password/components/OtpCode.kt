package com.vi.techshopmobile.presentation.authenticate.forget_password.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpCode(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    focusRequest: FocusRequester
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            //.padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                Color(0xffE1EFFE)
            )
            .wrapContentSize()
            .focusRequester(focusRequest),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(48.dp),
                contentAlignment = Alignment.Center,
            ) {
                if (value.text == ""){
                    Divider(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp, top = 0.dp, start = 8.dp, end = 8.dp),
                        thickness = 2.dp,
                        color = Color(0xff4F4F4F)
                    )
                }else{
                    innerTextField()
                }

            }
        },
        cursorBrush = SolidColor(Color.White),
        textStyle = MaterialTheme.typography.displayLarge.copy(
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.None
        ),
        keyboardActions = KeyboardActions(onDone = null)
    )
}

@Preview
@Composable
fun OtpCodeComponentPreview() {
    BasicTextField(
        value = "1",
        onValueChange = {},
        modifier = Modifier
            //.padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                Color(0xffE1EFFE)
            )
            .wrapContentSize()
            .size(width = 40.dp, height = 48.dp)
            .focusRequester(FocusRequester.Default),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                innerTextField()
            }
        },
        cursorBrush = SolidColor(Color.White),
        textStyle = MaterialTheme.typography.displayLarge.copy(
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.None
        ),
        keyboardActions = KeyboardActions(onDone = null)
    )
}

@Preview
@Composable
fun OtpCodeComponentNonValuePreview() {
    BasicTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            //.padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                Color(0xffE1EFFE)
            )
            .wrapContentSize()
            .size(width = 40.dp, height = 48.dp)
            .focusRequester(FocusRequester.Default),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Divider(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp, top = 0.dp, start = 8.dp, end = 8.dp),
                    thickness = 2.dp,
                    color = Color(0xff4F4F4F)
                )
            }
        },
        cursorBrush = SolidColor(Color.White),
        textStyle = MaterialTheme.typography.displayLarge.copy(
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.None
        ),
        keyboardActions = KeyboardActions(onDone = null)
    )
}