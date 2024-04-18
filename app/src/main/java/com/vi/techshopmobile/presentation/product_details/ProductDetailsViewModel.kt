package com.vi.techshopmobile.presentation.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import com.vi.techshopmobile.domain.usecases.wish_list.WishListUseCases
import com.vi.techshopmobile.presentation.products.ProductsViewState
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
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

    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()

    private val _quantityProduct = MutableStateFlow(1)
    val quantityProduct = _quantityProduct.asStateFlow()

    private val _itemInWishList = MutableStateFlow(false)
    val itemInWishList = _itemInWishList.asStateFlow()

    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.GetDetailEvent -> {
                getProductDetail(event.productLine)
            }

            is ProductDetailsEvent.AddItemToWishListEvent -> {
                viewModelScope.launch {
                    wishListUseCases.selectWishItem(
                        event.wishItem.username,
                        event.wishItem.productLine,
                    ).collect {
                        if (it == null || it.username != event.wishItem.username) {
                            wishListUseCases.upsertWishItem(event.wishItem)
                            EventBus.sendEvent(Event.Toast("Đã thêm vào danh sách yêu thích"))
                            this.cancel();
                        } else {
                            wishListUseCases.deleteWishItemByProductLine(
                                event.wishItem.username,
                                event.wishItem.productLine
                            )
                            EventBus.sendEvent(Event.Toast("Đã xóa khỏi danh sách yêu thích"))
                            this.cancel();
                        }
                    }
                }
            }

            is ProductDetailsEvent.AddItemToCart -> {
                viewModelScope.launch {
                    cartUseCases.getCartItem(event.cartItem.productLine).collect {
                        if (it == null || it.username != event.cartItem.username) {
                            cartUseCases.upsertCart(event.cartItem)
                        } else {
                            var quantity = it.quantity.plus(
                                _quantityProduct.value
                            )
                            cartUseCases.updateCart(
                                quantity, id = it.id
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

            is ProductDetailsEvent.DecreaseQuantity -> {
                viewModelScope.launch {
                    _quantityProduct.value = quantityProduct.value.minus(event.quantity)
                    this.cancel()
                }
            }

            is ProductDetailsEvent.RemoveItemWishList -> {
                viewModelScope.launch {
                    wishListUseCases.deleteWishItem(event.wishItem)
                    EventBus.sendEvent(Event.Toast("Đã xóa sản phẩm khỏi danh sách yêu thích"))
                }
            }

            is ProductDetailsEvent.SelectItemWishList -> {
                viewModelScope.launch {
                    wishListUseCases.selectWishItem(event.username, event.productLine).collectLatest {
                        if (it == null || it.username != event.username) {
                            _itemInWishList.value = false
                        }
                        else{
                            _itemInWishList.value = it.isSelected
                        }
                    }
                    EventBus.sendEvent(Event.Toast("đã chọn sản phẩm yêu thích với tên ${event.productLine}"))
                }
            }

            is ProductDetailsEvent.DeleteWishItemByProductLine -> TODO()
            is ProductDetailsEvent.GetProductsRandom -> {
                getProductsRandom(event.categoryName, event.num)
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

    fun getProductsRandom(categoryName: String, num: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            productUseCases.getProductsRandom(categoryName, num)
                .onRight { products ->
                    _state.update {
                        it.copy(
                            products = products
                        )
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(
                            error = error.detail
                        )
                    }
                    EventBus.sendEvent(Event.Toast(error.detail))
                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}