package com.vi.techshopmobile.presentation.filter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.usecases.categories.CategoriesUseCases
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val categoriesUseCases: CategoriesUseCases
) : ViewModel() {


    private val _state = MutableStateFlow(FilterViewState())
    val state = _state.asStateFlow()

    fun onEvent(event: FilterEvent) {
        when (event) {
            is FilterEvent.GetProducts -> {
                _state.update { filterViewState ->
                    filterViewState.copy(
                        products = event.products
                    )
                }
            }

            is FilterEvent.FilterProductByPrice -> {
                viewModelScope.launch {
                    val filteredProducts =
                        event.products.filter { productLine ->
                            productLine.price < event.end && productLine.price > event.start
                        }
                    _state.update { filterViewState ->
                        filterViewState.copy(
                            products = if (event.index.isEmpty()) {
                                event.products
                            } else if (event.index.size == 1) {
                                filteredProducts
                            } else {
                                filterViewState.products.plus(
                                    filteredProducts
                                ).distinctBy { it.id }
                            }
                        )
                    }
                }
            }

            is FilterEvent.FilterProductsByBrand -> {
                viewModelScope.launch {
                    _state.update { filterViewState ->
                        val updatedProducts = if (event.index.size == 1) {
                            event.products
                        } else {
                            state.value.products.plus(event.products);
                        }
                        filterViewState.copy(products = updatedProducts)
                    }

                }
            }

            is FilterEvent.FilterProductsByOption -> {
                viewModelScope.launch {
                    val filteredProducts = when (event.value) {
                        "1" -> sortProductsAscendingByPrice(state.value.products)
                        "2" -> sortProductsDescendingByPrice(state.value.products)
                        "3" -> sortProductsAlphabetically(state.value.products)
                        "4" -> sortProductsRevertAlphabetically(state.value.products)
                        else -> state.value.products
                    }
                    _state.update { filterViewState ->
                        filterViewState.copy(
                            products = filteredProducts

                        )
                    }
                }
            }
        }
    }

    private fun sortProductsAscendingByPrice(products: List<ProductLine>): List<ProductLine> {
        return products.sortedBy { productLine -> productLine.price }
    }

    private fun sortProductsDescendingByPrice(products: List<ProductLine>): List<ProductLine> {
        return products.sortedBy { productLine -> -productLine.price }
    }

    private fun sortProductsAlphabetically(products: List<ProductLine>): List<ProductLine> {
        return products.sortedBy { productLine -> productLine.productName }
    }

    private fun sortProductsRevertAlphabetically(products: List<ProductLine>): List<ProductLine> {
        return products.sortedBy { productLine -> productLine.productName }.reversed()
    }

}