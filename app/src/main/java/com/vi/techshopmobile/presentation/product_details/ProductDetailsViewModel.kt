package com.vi.techshopmobile.presentation.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import com.vi.techshopmobile.domain.usecases.wish_list.WishListUseCases
import com.vi.techshopmobile.presentation.categories.CategoriesEvents
import com.vi.techshopmobile.presentation.products.ProductsViewState
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productUseCases: ProductUseCases,
    private val wishListUseCases: WishListUseCases
) : ViewModel() {
    private val _productDetail = MutableStateFlow(ProductDetailsViewState())
    val productDetail = _productDetail.asStateFlow();

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