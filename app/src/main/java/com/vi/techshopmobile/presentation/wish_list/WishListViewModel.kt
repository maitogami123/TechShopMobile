package com.vi.techshopmobile.presentation.wish_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import com.vi.techshopmobile.domain.usecases.wish_list.WishListUseCases
import com.vi.techshopmobile.presentation.product_details.ProductDetailsViewState
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListUseCases: WishListUseCases,
    private val productUseCases: ProductUseCases,
) : ViewModel() {
    //
//    private val _productDetail = MutableStateFlow(ProductDetailsViewState())
//    val productDetail = _productDetail.asStateFlow();
    private val _state = MutableStateFlow(emptyList<WishItem>())
    val state = _state.asStateFlow()
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()
    private val _productDetail = MutableStateFlow(emptyList<ProductDetailResponse>())
    val productDetail = _productDetail.asStateFlow()
    suspend fun onEvent(event: WishListEvents) {
        when (event) {
            is WishListEvents.GetUserWishList -> {
                wishListUseCases.getWishList(event.username).onEach {
                    _state.value = it.reversed()
                    for (wishItem in it) {
                        var productResponse = productUseCases.getProductDetail(wishItem.productLine)
                        productResponse.onRight { prodRes ->
                            val updatedList = _productDetail.value.toMutableList()
                            updatedList.add(prodRes)
                            _productDetail.value = updatedList;
                        }
                    }
                }.launchIn(viewModelScope)
            }

            is WishListEvents.GetDetailEvent -> {
                state.value.forEach {
                    _productDetail.value.plus(productUseCases.getProductDetail(it.productLine).right())
                }
            }
        }
    }


//    fun getProductDetail(productLine: String) {
//        viewModelScope.launch {
//            _loadingState.value = true
//            _productDetail.update {
//                it.copy(isLoading = true)
//            }
//            delay(2000L)
//            productUseCases.getProductDetail(productLine)
//                .onRight { productDetailResponse ->
//                    _productDetail.update {
//                        it.copy(
//                            productDetail = productDetailResponse
//                        )
//                    }
//                }
//                .onLeft { error ->
//                    _productDetail.update {
//                        it.copy(
//                            error = error.detail
//                        )
//                    }
//                    EventBus.sendEvent(Event.Toast(error.detail))
//                }
//            _loadingState.value = false
//            _productDetail.update {
//                it.copy(isLoading = false)
//            }
//        }
//    }
}