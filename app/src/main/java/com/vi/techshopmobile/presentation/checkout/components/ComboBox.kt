package com.vi.techshopmobile.presentation.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vi.techshopmobile.LocalProvinces
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.Provinces
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.Gray_300

@Composable
fun ComboBox(
    modifier: Modifier = Modifier,
    provinces: List<Provinces>,
    mSelectedText: String,
    errorMessage: String? = null,
    placeHolderText: String? = null,
    onChange: (changedValue: String) -> Unit
) {
    val collectionType = object : TypeToken<List<Provinces?>?>() {}.type
    val json: List<Provinces> = Gson().fromJson(
        LocalProvinces.current,
        collectionType
    )
    var mExpanded by remember {
        mutableStateOf(false)
    }

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
            horizontalArrangement = Arrangement.Start
        ) {
            if (errorMessage != null)
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = errorMessage,
                    style = MaterialTheme.typography.labelLarge.copy(color = Danger)
                )
        }
        // Create an Outlined Text Field
        // with icon and not expanded
        TextField(
            enabled = false,
            value = mSelectedText,
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
            onValueChange = {
                isFocused = true
                //mSelectedText = it
                onChange(it)
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
                .fillMaxWidth()
                .fillMaxHeight(.3f)
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            provinces.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(text = label.name, textAlign = TextAlign.Center)
                    },
                    onClick = {
                        isFocused = true
                        //mSelectedText = label.name
                        onChange(label.name)
                        mExpanded = false
                    }
                )
            }
        }

    }
}

@Composable
fun ComboBoxQuestion(
    modifier: Modifier = Modifier,
    list: List<String>,
    mSelectedText: String,
    errorMessage: String? = null,
    placeHolderText: String? = null,
    onChange: (changedValue: String) -> Unit,
    onClick: () -> Unit
) {
    var mExpanded by remember {
        mutableStateOf(false)
    }

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
        modifier = Modifier
            .clickable { mExpanded = !mExpanded },
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (errorMessage != null) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = errorMessage,
                    style = MaterialTheme.typography.labelLarge.copy(color = Danger)
                )
            }
        }
        // Create an Outlined Text Field
        // with icon and not expanded
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                enabled = false,
                value = mSelectedText,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    }
                    .weight(.8f)
                ,
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
                onValueChange = {
                    isFocused = true
                    //mSelectedText = it
                    onChange(it)
                },
                leadingIcon = {
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
            Icon(
                modifier = Modifier.clickable {
                    onClick()
                }
                ,
                imageVector = Icons.Rounded.Send,
                contentDescription = ""
            )
        }

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.3f)
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(text = label, textAlign = TextAlign.Center)
                    },
                    onClick = {
                        isFocused = true
                        //mSelectedText = label.name
                        onChange(label)
                        mExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ComboBoxDistrict(
    modifier: Modifier = Modifier,
    provinces: List<Provinces>,
    mSelectedText: String,
    citySelected: String,
    errorMessage: String? = null,
    placeHolderText: String? = null,
    onChange: (changedValue: String) -> Unit
) {
    var mExpanded by remember {
        mutableStateOf(false)
    }

    // Create a list of cities
    //val mCities = json.listIterator()
    // Create a string value to store the selected city
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
            horizontalArrangement = Arrangement.Start
        ) {
            if (errorMessage != null)
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = errorMessage,
                    style = MaterialTheme.typography.labelLarge.copy(color = Danger)
                )
        }
        // Create an Outlined Text Field
        // with icon and not expanded
        TextField(
            enabled = false,
            value = mSelectedText,
            onValueChange = {
                //mSelectedText = it
                isFocused = true
                onChange(it)
            },
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
                .fillMaxWidth()
                .fillMaxHeight(.3f)
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            provinces.find { it.name == citySelected }?.districts?.forEach { label ->
                DropdownMenuItem(
                    text = {
//                        Text(text = label)
                        Text(
                            text = label.name,
                            textAlign = TextAlign.Center
                        )
                    },
                    onClick = {
                        isFocused = true
                        onChange(label.name)
                        //mSelectedText = label.name
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
    val listQuestion = listOf(
        "Bạn muốn biết thêm thông tin gì?",
        "Hi TechShop",
        "What is your store address?",
        "What type of technology products do you sell?",
    )
    var question by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        ComboBoxQuestion(
            list = listQuestion,
            mSelectedText = if (question.isEmpty()) listQuestion[0] else question,
            onChange = { question = it }) {}
//        ComboBox(placeHolderText = "Tỉnh")
//        ComboBox(placeHolderText = "Tỉnh", errorMessage = "Vui lòng chọn tỉnh")
    }

}