package com.vi.techshopmobile.presentation.authenticate.forget_password.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.util.connectInputtedCode
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RowOtpCode(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(top = 58.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in textList.indices) {
                OtpCode(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    value = textList[i].value,
                    onValueChange = { newValue ->
                        //if old value is not empty, just return
                        if (textList[i].value.text != "") {
                            if (newValue.text == "") {
                                //before return, if the new value is empty, set the text field to empty
                                textList[i].value = TextFieldValue(
                                    text = "",
                                    selection = TextRange(0)
                                )
                            }

                            return@OtpCode
                        }

                        //set new value and move cursor to the end
                        textList[i].value = TextFieldValue(
                            text = newValue.text,
                            selection = TextRange(newValue.text.length)
                        )

                        focusManager.clearFocus()
                        keyboardController?.hide()

                        nextFocus(textList, requesterList)
                    },
                    focusRequest = requesterList[i]
                )
            }
        }
    }


    LaunchedEffect(key1 = null, block = {
        delay(300)
        requesterList[0]
    })
}

fun nextFocus(textList: List<MutableState<TextFieldValue>>, requesterList: List<FocusRequester>) {
    for (index in textList.indices) {
        if (textList[index].value.text == "") {
            if (index < textList.size) {
                requesterList[index].requestFocus()
                break
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun RowOtpCodeReview() {
    RowOtpCode(
        textList = listOf(
            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange.Zero
                )
            ),
            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange.Zero
                )
            ),
            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange.Zero
                )
            ),
            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange.Zero
                )
            ),
            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange.Zero
                )
            ),
            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange.Zero
                )
            ),
        ), requesterList = listOf(
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
        )
    )
}