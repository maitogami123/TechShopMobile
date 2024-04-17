package com.vi.techshopmobile.presentation.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.presentation.Dimens
import com.vi.techshopmobile.presentation.Dimens.ExtraSmallPadding2
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.SearchBar
import com.vi.techshopmobile.presentation.home.home_navigator.navigateToSearchResult
import com.vi.techshopmobile.ui.theme.Blue_100
import com.vi.techshopmobile.ui.theme.Blue_50
import com.vi.techshopmobile.ui.theme.Gray_500
import com.vi.techshopmobile.ui.theme.Gray_900
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.decodeToken

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel: SearchViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val decodedToken = decodeToken(LocalToken.current)
    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(SearchEvent.GetUserSearchHistory(decodedToken.sub))
    }

    var searchQuery by remember {
        mutableStateOf("")
    }
    Scaffold(modifier = Modifier.padding(horizontal = SmallPadding, vertical = ExtraSmallPadding2),
        topBar = {
            SearchBar(text = searchQuery,
                readOnly = false,
                onValueChange = { searchQuery = it },
                onSearch = {
                    if (searchQuery.isNotBlank()) {
                        navigateToSearchResult(
                            navController = navController,
                            searchQuery = searchQuery,
                            emptyList(),
                            isFilter = false
                        )
                        (viewModel::onEvent)(
                            SearchEvent.AddSearchStringToList(
                                SearchHistory(
                                    searchString = searchQuery, username = decodedToken.sub
                                )
                            )

                        )
                        searchQuery = ""
                    } else {
                        Event.Toast("Nhập vào từ khóa bạn muốn tìm kiếm")
                    }
                })

        }) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier.padding(top = topPadding + Dimens.SmallPadding)
        ) {
            Column {
                Text(
                    text = "Lịch sử tìm kiếm",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Gray_900,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                state.value.reversed().forEachIndexed { index, searchString ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(vertical = 4.dp)
                        .clickable {
                            navigateToSearchResult(
                                navController = navController,
                                searchQuery = searchString.searchString,
                                emptyList(),
                                isFilter = false
                            )
                        }) {
                        Text(
                            text = searchString.searchString,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Gray_500,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                    }
                    if (index != searchString.searchString.lastIndex) {
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            Row(
                modifier = Modifier.padding(vertical = Dimens.ExtraSmallGap)
            ) {
                Spacer(
                    modifier = Modifier
                        .background(color = Blue_50)
                        .height(16.dp)
                        .fillMaxWidth()
                )
            }

            LazyColumn {
                item {
                    Text(
                        text = "Sản phẩm gợi ý",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Gray_900,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimens.ExtraSmallGap)
                    )
                }
            }

        }
    }
}