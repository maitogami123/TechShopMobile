package com.vi.techshopmobile.presentation.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.presentation.Dimens.ExtraSmallPadding2
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.presentation.common.SearchBar
import com.vi.techshopmobile.ui.theme.Gray_500
import com.vi.techshopmobile.ui.theme.Gray_900
import com.vi.techshopmobile.util.decodeToken

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel: SearchViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val productsSearch = viewModel.productsSearch.collectAsState()
    val decodedToken = decodeToken(LocalToken.current)
    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(SearchEvent.GetUserSearchHistory(decodedToken.sub))
    }

    var searchQuery by remember {
        mutableStateOf("")
    }
    Log.d("Search Product", productsSearch.value.productsSearch.toString())
    Scaffold(
        modifier = Modifier.padding(horizontal = SmallPadding, vertical = ExtraSmallPadding2),
        topBar = {
            SearchBar(
                text = searchQuery,
                readOnly = false,
                onValueChange = { searchQuery = it },
                onSearch = {
                    (viewModel::onEvent)(
                        SearchEvent.GetProductSearch(SearchProduct(search = searchQuery))
                    )
                    (viewModel::onEvent)(
                        SearchEvent.AddSearchStringToList(
                            SearchHistory(
                                searchString = searchQuery,
                                username = decodedToken.sub
                            )
                        )

                    )

                })

        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier.padding(top = topPadding)
        ) {
            Column {
                Text(
                    text = "Lịch sử tìm kiếm",
                    style = MaterialTheme.typography.displayMedium,
                    color = Gray_900
                )
                state.value.forEachIndexed { index, searchString ->
                    Text(
                        text = searchString.searchString,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray_500,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                (viewModel::onEvent)(
                                    SearchEvent.GetProductSearch(SearchProduct(search = searchString.searchString))
                                )
                            })
                    if (index != searchString.searchString.lastIndex) {
                        Divider()
                    }
                }
            }
            LazyColumn {

            }

        }
    }
}