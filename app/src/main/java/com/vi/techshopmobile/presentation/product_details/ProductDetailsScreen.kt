package com.vi.techshopmobile.presentation.product_details

import android.widget.Space
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.common.Accordion
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.Constants.BASE_URL
import com.vi.techshopmobile.util.formatPrice

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailsScreen(
    productLine: String,
    onNavigateUp: () -> Unit
) {
    val viewModel: ProductDetailsViewModel = hiltViewModel()
    val state by viewModel.productDetail.collectAsState();

    LaunchedEffect(key1 = productLine) {
        (viewModel::onEvent)(ProductDetailsEvent.GetDetailEvent(productLine))
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                rightBtnIcon = R.drawable.ic_share
            ) {
                onNavigateUp()
            }
        },
        bottomBar = {
            FloatingBottomBar(
                buttonText = "Mua ngay",
                onButtonClick = {},
                onAddToWishList = {},
                onAddToCart = {})
        }
    ) {
        val paddingTop = it.calculateTopPadding()

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            if (state.isLoading) {
                LoadingDialog(isLoading = state.isLoading)
            } else {
                if (state.productDetail != null) {
                    val pagerState = rememberPagerState(
                        pageCount = { state.productDetail!!.imageUris.size },
                        initialPage = 0,
                    )
                    ConstraintLayout {
                        val (indicator) = createRefs()
                        HorizontalPager(state = pagerState) {
                            AsyncImage(
                                model = BASE_URL + "product/get-file?filePath=" + state.productDetail!!.imageUris[it],
                                contentDescription = null,
                                modifier = Modifier.height(412.dp),
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            )
                        }
                        Row(
                            Modifier
                                .constrainAs(indicator) {
                                    bottom.linkTo(parent.bottom)
                                }
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val isSelected = (pagerState.currentPage == iteration)
                                val color =
                                    if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .animateContentSize()
                                        .width(if (isSelected) 32.dp else 8.dp)
                                        .height(8.dp)
                                )
                            }
                        }
                    }
                    Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text(
                            text = state.productDetail!!.product.productName,
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(
                            text = formatPrice(state.productDetail!!.product.price),
                            style = MaterialTheme.typography.displayMedium.copy(color = Danger)
                        )
                        Accordion(
                            heading = "Thông tin sản phẩm",
                            items = state.productDetail!!.productInfos.map { item -> item.productInformation }
                        )
                    }

                }
                Spacer(Modifier.height(it.calculateBottomPadding()))
            }
        }
        // TODO: Product Suggestion
    }
}

@Preview
@Composable
private fun PreviewProductDetails() {
    TechShopMobileTheme {
        ProductDetailsScreen(productLine = "Next level") {
        }
    }
}