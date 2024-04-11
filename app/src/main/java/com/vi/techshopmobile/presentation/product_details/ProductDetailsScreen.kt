package com.vi.techshopmobile.presentation.product_details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.presentation.common.Accordion
import com.vi.techshopmobile.presentation.common.FloatingBottomBar
import com.vi.techshopmobile.presentation.common.LoadingDialog
import com.vi.techshopmobile.presentation.home.home_navigator.component.UtilityTopNavigation
import com.vi.techshopmobile.presentation.navgraph.LocalNavGraphController
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.ui.theme.Danger
import com.vi.techshopmobile.ui.theme.Gray_500
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.Constants.BASE_URL
import com.vi.techshopmobile.util.decodeToken
import com.vi.techshopmobile.util.formatPrice
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    navController: NavController,
    isLoggedIn: Boolean = false,
    productLine: String,
    onNavigateUp: () -> Unit
) {
    val viewModel: ProductDetailsViewModel = hiltViewModel()
    val navGraphController = LocalNavGraphController.current;
    val state by viewModel.productDetail.collectAsState();

    val quantityToAdd by viewModel.quantityProduct.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    val decodedToken = decodeToken(LocalToken.current)

    var quantity: Int by remember {
        mutableStateOf(1)
    }
    var statusMinusBtn: Boolean by remember {
        mutableStateOf(true)
    }
    var statusPlusBtn: Boolean by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = productLine) {
        (viewModel::onEvent)(ProductDetailsEvent.GetDetailEvent(productLine))
    }

    Scaffold(
        topBar = {
            UtilityTopNavigation(
                onRightBtnClick = {

                },
                leftBtnIcon = R.drawable.ic_left_arrow,
                rightBtnIcon = R.drawable.ic_share
            ) {
                onNavigateUp()
            }
        },
        bottomBar = {
            if (isLoggedIn) {
                FloatingBottomBar(
                    buttonText = "Mua ngay",
                    onButtonClick = {
                        (viewModel::onEvent)(
                            ProductDetailsEvent.AddItemToCart(
                                CartItem(
                                    brandName = state.productDetail!!.brandName,
                                    categoryName = state.productDetail!!.categoryName,
                                    thumbnailUri = state.productDetail!!.thumbnailUri,
                                    price = (
                                            state.productDetail!!.product.price -
                                                    ((state.productDetail!!.product.price * (state.productDetail!!.product.discount / 100)))
                                            ),
                                    productName = state.productDetail!!.product.productName,
                                    productLine = productLine,
                                    username = decodedToken.sub,
                                    quantity = quantity,
                                    stock = state.productDetail!!.stock
                                )
                            )
                        )
                        navController.navigate(Route.CartScreen.route)
                    },
                    onAddToWishList = {
                        (viewModel::onEvent)(
                            ProductDetailsEvent.AddItemToWishListEvent(
                                WishItem(
                                    productLine = productLine,
                                    productName = state.productDetail!!.product.productName,
                                    username = decodedToken.sub
                                )
                            )
                        )
                    },
                    onAddToCart = {
                        showBottomSheet = true
                    })
            } else {
                FloatingBottomBar(buttonText = "Đăng nhập") {
                    navGraphController.navigate(Route.AuthenticateNavigation.route)
                }
            }
        }
    ) {
        val paddingTop = it.calculateTopPadding()

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
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
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = state.productDetail!!.product.productName,
                            style = MaterialTheme.typography.displayMedium
                        )
                        if (state.productDetail!!.product.discount > 0) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = formatPrice(state.productDetail!!.product.price),
                                style = MaterialTheme.typography.displaySmall.copy(textDecoration = TextDecoration.LineThrough),
                                color = Gray_500
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = formatPrice(
                                    state.productDetail!!.product.price -
                                            ((state.productDetail!!.product.price * (state.productDetail!!.product.discount / 100)))
                                ),
                                style = MaterialTheme.typography.displayMedium,
                                color = Danger
                            )
                        } else {
                            Text(
                                text = formatPrice(state.productDetail!!.product.price),
                                style = MaterialTheme.typography.displayMedium.copy(color = Danger)
                            )
                        }
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
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            // Sheet content
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // TODO - START: Make this block of code a component
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                ) {
                    AsyncImage(
                        model = BASE_URL + "product/get-file?filePath=" + state.productDetail!!.thumbnailUri,
                        contentDescription = null,
                        modifier = Modifier.size(96.dp),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = state.productDetail!!.product.productName,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "16.000.000 VND",
                            style = MaterialTheme.typography.displaySmall,
                            color = Danger
                        )
                        Text(
                            text = "Còn lại: ${state.productDetail!!.stock}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Số lượng", modifier = Modifier.weight(1f))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // TODO: Make increase and decrease quantity buttons functional, create a state to store current quantity
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = null,
                            Modifier
                                .alpha(
                                    if (quantity > 1) 1f else 0.4f
                                )
                                .clickable(
                                    enabled = if (quantity > 1) statusMinusBtn else !statusMinusBtn
                                ) {
                                    quantity = quantity.minus(1)
                                    (viewModel::onEvent)(
                                        ProductDetailsEvent.DecreaseQuantity(1)
                                    )
                                }
                        )

                        Text(text = quantity.toString())
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = null,
                            Modifier
                                .alpha(if (state.productDetail?.stock!! > quantity) 1f else 0.4f)
                                .clickable(enabled = if (state.productDetail?.stock!! > quantity) statusPlusBtn else !statusPlusBtn) {
                                    quantity = quantity.plus(1)
                                    (viewModel::onEvent)(
                                        ProductDetailsEvent.IncreaseQuantity(1)
                                    )
                                }
                        )
                    }
                }
                // TODO - END

                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    // TODO: Dismiss bottom sheet box then add the product into user cart.
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                            (viewModel::onEvent)(
                                ProductDetailsEvent.AddItemToCart(
                                    CartItem(
                                        brandName = state.productDetail!!.brandName,
                                        categoryName = state.productDetail!!.categoryName,
                                        thumbnailUri = state.productDetail!!.thumbnailUri,
                                        price = (
                                                state.productDetail!!.product.price -
                                                        ((state.productDetail!!.product.price * (state.productDetail!!.product.discount / 100)))
                                                ),
                                        productName = state.productDetail!!.product.productName,
                                        productLine = productLine,
                                        username = decodedToken.sub,
                                        quantity = quantity,
                                        stock = state.productDetail!!.stock
                                    )
                                )
                            )
                        }
                    }
                }) {
                    Text("Thêm vào giỏ hàng")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProductDetails() {
    TechShopMobileTheme {
        ProductDetailsScreen(productLine = "Next level", navController = rememberNavController()) {
        }
    }
}