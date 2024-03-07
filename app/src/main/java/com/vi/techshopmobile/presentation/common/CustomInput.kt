package com.vi.techshopmobile.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.RadiusSmall
import com.vi.techshopmobile.ui.theme.Gray_300
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun Input(
    modifier: Modifier = Modifier,
    labelText: String? = null,
    placeHolderText: String? = null,
    inputText: String,
    onChange: (changedValue: String) -> Unit
) {
    if (labelText != null) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
    TextField(modifier = modifier
        .clip(RoundedCornerShape(RadiusSmall))
        .border(
            width = 1.dp,
            color = Gray_300,
            shape = RoundedCornerShape(RadiusSmall)
        ),
        singleLine = true,
        placeholder = {
            if (placeHolderText != null)
                Text(
                    text = placeHolderText,
                    style = MaterialTheme.typography.displaySmall
                )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        value = inputText,
        onValueChange = {
            onChange(it)
        })
}

@Composable
fun TransformableInput(
    modifier: Modifier = Modifier,
    labelText: String? = null,
    placeHolderText: String? = null,
    inputText: String,
    onChange: (changedValue: String) -> Unit
) {
    var hidden by rememberSaveable { mutableStateOf(true) }

    if (labelText != null) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
    TextField(modifier = modifier
        .clip(RoundedCornerShape(RadiusSmall))
        .border(
            width = 1.dp,
            color = Gray_300,
            shape = RoundedCornerShape(RadiusSmall)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (!hidden) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        trailingIcon = {
            val icon = if (hidden)
                R.drawable.ic_show
            else R.drawable.ic_hidden

            // Please provide localized description for accessibility services
            val description = if (!hidden) "Hide password" else "Show password"

            IconButton(onClick = { hidden = !hidden }) {
                Icon(painter = painterResource(id = icon), description)
            }
        },
        placeholder = {
            if (placeHolderText != null)
                Text(
                    text = placeHolderText,
                    style = MaterialTheme.typography.displaySmall
                )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        value = inputText,
        onValueChange = {
            onChange(it)
        })
}

@Preview
@Composable
fun PreviewInput() {
    TechShopMobileTheme {

        var testInput by remember {
            mutableStateOf("hello")
        }

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Input(
                inputText = testInput,
                labelText = "Tài khoản",
                placeHolderText = "Nhập tên tài khoản",
                modifier = Modifier.fillMaxWidth()
            ) {
                testInput = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            Input(
                inputText = testInput,
                placeHolderText = "Nhập tên tài khoản",
                modifier = Modifier.fillMaxWidth()
            ) {
                testInput = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            TransformableInput(
                labelText = "Mật khẩu",
                inputText = testInput,
                modifier = Modifier.fillMaxWidth()
            ) {
                testInput = it
            }
        }

    }
}