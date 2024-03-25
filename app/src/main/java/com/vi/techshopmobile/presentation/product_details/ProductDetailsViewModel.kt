package com.vi.techshopmobile.presentation.product_details

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import com.vi.techshopmobile.domain.usecases.wish_list.WishListUseCases
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productUseCases: ProductUseCases,
    private val wishListUseCases: WishListUseCases,
    private val cartUseCases: CartUseCases
) : ViewModel() {
    private val _productDetail = MutableStateFlow(ProductDetailsViewState())
    val productDetail = _productDetail.asStateFlow();

    private val _quantityProduct = MutableStateFlow(1)
    val quantityProduct = _quantityProduct.asStateFlow()

    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.GetDetailEvent -> {
                getProductDetail(event.productLine)
            }

            is ProductDetailsEvent.AddItemToWishListEvent -> {
                viewModelScope.launch {
                    wishListUseCases.upsertWishItem(event.wishItem)
                    EventBus.sendEvent(Event.Toast("Đã thêm vào danh sách yêu thích"))
                }
            }

            is ProductDetailsEvent.AddItemToCart -> {
                viewModelScope.launch {
                    cartUseCases.getCartItem(event.cartItem.productLine).collect {
                        if (it == null) {
                            cartUseCases.upsertCart(event.cartItem)
                        } else {
                            cartUseCases.upsertCart(
                                event.cartItem.copy(
                                    quantity = it.quantity?.plus(
                                        _quantityProduct.value
                                    )
                                )
                            )
                        }
                        EventBus.sendEvent(Event.Toast("Đã thêm vào giỏ hàng"))
                        this.cancel();
                    }

                }
            }

            is ProductDetailsEvent.IncreaseQuantity -> {
                viewModelScope.launch {
                    _quantityProduct.value = quantityProduct.value.plus(event.quantity)
                    this.cancel()
                }
            }
        }
    }

    fun getProductDetail(productLine: String) {
        viewModelScope.launch {
            _productDetail.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            productUseCases.getProductDetail(productLine)
                .onRight { productDetailResponse ->
                    _productDetail.update {
                        it.copy(
                            productDetail = productDetailResponse
                        )
                    }
                }
                .onLeft { error ->
                    _productDetail.update {
                        it.copy(
                            error = error.detail
                        )
                    }
                    EventBus.sendEvent(Event.Toast(error.detail))
                }

            _productDetail.update {
                it.copy(isLoading = false)
            }
        }
    }
}