package com.vi.techshopmobile.presentation.wish_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.util.decodeToken

@Composable
fun WishListScreen() {
    val viewModel: WishListViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val decodedToken = decodeToken(LocalToken.current)
    LaunchedEffect(key1 = null) {
        (viewModel::onEvent)(WishListEvents.GetUserWishList(decodedToken.sub))
    }

    Column {
        Text(text = "${decodedToken.sub}'s wishList")
        Divider()
        state.value.forEach {
            Column() {
                Text(text = it.productName)
                Text(text = it.productLine)
                Text(text = it.username)
                Text(text = it.quantity.toString())
            }
            Divider()
        }
    }
}