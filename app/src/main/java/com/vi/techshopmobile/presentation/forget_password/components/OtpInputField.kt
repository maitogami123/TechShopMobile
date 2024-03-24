package com.vi.techshopmobile.presentation.forget_password.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vi.techshopmobile.presentation.authenticate.forget_passwo.KeyboardStatus
import com.vi.techshopmobile.presentation.authenticate.forget_passwo.keyboardAsState


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpLength: Int,
    onOtpChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var otpValue by remember {
        mutableStateOf("")
    }

    val keyboardState = keyboardAsState(KeyboardStatus.Closed)

    val isShowWarning by remember(keyboardState) {
        derivedStateOf {
            if (keyboardState.value == KeyboardStatus.Closed) {
                if (otpValue.length != otpLength) {
                    return@derivedStateOf true
                }
            }
            false

        }
    }

    val focusRequester = remember {
        FocusRequester()
    }

    BasicTextField(
        modifier = Modifier.focusRequester(focusRequester),
        value = otpValue,
        onValueChange = { value ->
            if (value.length <= otpLength) {
                otpValue = value
                onOtpChanged(otpValue)
            }
        },
        decorationBox = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 58.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpLength) { index ->
                    val char = when {
                        index >= otpValue.length -> ""
                        else -> otpValue[index].toString()
                    }

                    val isFocus = index == otpValue.length
                    OtpCell(
                        char = char,
                        isFocus = isFocus,
                        isShowWarning = isShowWarning,
                        modifier = Modifier
                            .weight(
                                1f
                            )
                            .padding(horizontal = 5.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }
        )
    )

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

}

@Composable
fun OtpCell(
    char: String,
    isFocus: Boolean,
    isShowWarning: Boolean,
    modifier: Modifier = Modifier
) {

    val borderColor = if (isShowWarning) {
        MaterialTheme.colorScheme.error
    } else if (isFocus) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    BasicTextField(
        value = char,
        onValueChange = {},
        enabled = false,
        modifier = modifier
            .padding(horizontal = 4.dp)
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
                if (char == "") {
                    Divider(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp, top = 0.dp, start = 8.dp, end = 8.dp),
                        thickness = 2.dp,
                        color = Color(0xff4F4F4F)
                    )
                } else {
                    innerTextField()
                }
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


@Preview(showBackground = true)
@Composable
fun OtpInputFieldPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(24.dp)) {
            OtpInputField(otpLength = 6, onOtpChanged = {})
        }
    }
}

@Preview(name = "OptCell Focus", showBackground = true)
@Composable
fun OtpCellFocusPreview(
) {

    MaterialTheme {
        Box(modifier = Modifier) {
            OtpCell(char = "", isFocus = true, isShowWarning = false)
        }
    }

}