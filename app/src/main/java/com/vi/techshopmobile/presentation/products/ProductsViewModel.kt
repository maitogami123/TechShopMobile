package com.vi.techshopmobile.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.repository.products.ProductsRepository
import com.vi.techshopmobile.domain.usecases.categories.CategoriesUseCases
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import com.vi.techshopmobile.presentation.categories.CategoryProduct
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import com.vi.techshopmobile.util.EventBus.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productUseCases: ProductUseCases,
    private val categoryUseCases: CategoriesUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getProducts()
        categoryUseCases.getCategories
    }

    fun onEvent(event: ProductsEvents) {
        when (event) {
            is ProductsEvents.GetAllEventProduct -> {
                getProducts()
            }
            is ProductsEvents.GetAllProductByCategory -> {
                getProductCategory(event.categoryName)
            }

            is ProductsEvents.GetAllProductByBrand -> {}
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            productUseCases.getProducts()
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
                    sendEvent(Event.Toast(error.detail))
                }

            _state.update {
                it.copy(isLoading = false)
            }
            _isLoading.value = false
        }
    }
    private fun getProductCategory(categoryName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            categoryUseCases.getCategoryProducts(categoryName)
                .onRight { response ->
                    _state.update { productsViewState ->
                        productsViewState.copy(
                            productsOfCategory = productsViewState.productsOfCategory + ProductsOfCategory(response.name ,response.products)
                        )
                    }
                }
                .onLeft { error ->
                    EventBus.sendEvent(Event.Toast(error.detail))
                }

            _state.update {
                it.copy(isLoading = false)
            }
            _isLoading.value = false
        }
    }
}