package com.vi.techshopmobile.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.vi.techshopmobile.presentation.Dimens.ExtraSmallPadding2
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.SearchBar

@Composable
fun SearchScreen() {

    var searchQuery by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.padding(horizontal = SmallPadding, vertical = ExtraSmallPadding2),
        topBar = {
            SearchBar(text = searchQuery, readOnly = false, onValueChange = { searchQuery = it }) {
            }
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier.padding(top = topPadding)
        ) {
            Text(text = "Search Something")
        }
    }
}