package com.vi.techshopmobile.presentation.checkout.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.google.gson.Gson
import com.vi.techshopmobile.domain.model.ListProvinces
import com.vi.techshopmobile.domain.model.Provinces
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.Gray_300
import java.io.File
import java.io.InputStream
import kotlin.math.log

@Composable
fun ComboBox(
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    placeHolderText: String? = null,
) {
    var mExpanded by remember {
        mutableStateOf(false)
    }
    // Create a list of cities
    val mCities = listOf("provinces","ấc","dádas","dád")
    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var isFocused by rememberSaveable {
        mutableStateOf(true);
    }

    LaunchedEffect(key1 = errorMessage) {
        if (errorMessage != null) {
            isFocused = false
        }
    }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown




    Column(
        Modifier
            .clickable { mExpanded = !mExpanded }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.End
        ) {
            if (errorMessage != null)
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.labelLarge.copy(color = Danger)
                )
        }
        // Create an Outlined Text Field
        // with icon and not expanded
        TextField(
            enabled = false,
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {
                if (placeHolderText != null) {
                    Text(
                        text = placeHolderText,
                        style = MaterialTheme.typography.displaySmall.copy(
                            color = if (!isFocused) Danger else Gray_300,
                            fontSize = 16.sp
                        )
                    )
                }
            },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                disabledLabelColor = Color.Black,
                disabledPlaceholderColor = Color.Black,
                disabledTrailingIconColor = Color.Black,
                disabledTextColor = Color.Black,
                disabledIndicatorColor = Color(0xff8A8A8A)
            ),
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ComboBoxPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        ComboBox(placeHolderText = "Tỉnh")
        ComboBox(placeHolderText = "Tỉnh", errorMessage = "Vui lòng chọn tỉnh")
    }

}