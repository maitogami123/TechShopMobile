package com.vi.techshopmobile.presentation.filter.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun FilterCheckBox() {
    var selectedItem by remember { mutableStateOf("") }
    var isSelected by remember {
        mutableStateOf(false)
    }
    LazyColumn(){

    }
}