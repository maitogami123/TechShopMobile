package com.vi.techshopmobile.presentation.products

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("SuspiciousIndentation")
@Composable
fun RowProductsComponent() {
    val viewModel: ProductsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    val state by viewModel.state.collectAsStateWithLifecycle()

//    SwipeRefresh(
//        state = swipeRefreshState,
//        onRefresh = { (viewModel::onEvent)(ProductsEvents.getOnEventProduct) },
//        indicator = { state, refreshTrigger ->
//            SwipeRefreshIndicator(
//                state = state,
//                refreshTriggerDistance = refreshTrigger,
//                backgroundColor = Color.White,
//                contentColor = Color.DarkGray
//            )
//        },
//    ) {
//
//    }

        RowProductsItem(products = state.products)
    }

