package com.vi.techshopmobile.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import com.vi.techshopmobile.domain.usecases.search.SearchUseCases
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
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases,
    private val productUseCases: ProductUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<SearchHistory>())
    val state = _state.asStateFlow()

    private val _productsSearch = MutableStateFlow(SearchViewState())
    val productsSearch = _productsSearch.asStateFlow()
    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetUserSearchHistory -> {
                searchUseCases.getSearchHistory(event.username).onEach {
                    _state.value = it.reversed()
                }.launchIn(viewModelScope)
            }

            is SearchEvent.AddSearchStringToList -> {
                viewModelScope.launch {
                    searchUseCases.upsetSearchHistory(event.searchHistory)
                }

            }
            is SearchEvent.GetProductSearch -> {
                getProductsSearch(event.searchProduct)
            }
        }
    }

    private fun getProductsSearch(searchProduct: SearchProduct) {
        viewModelScope.launch {
            _productsSearch.update {
                it.copy(isLoading = true)
            }
            delay(2000L)

            productUseCases.getSearchProducts(searchProduct)
                .onRight { searchResponse ->
                    _productsSearch.update {
                        it.copy(productsSearch = searchResponse)
                    }
                }
                .onLeft { error ->
                    _productsSearch.update {
                        it.copy(isError = error.detail)
                    }
                    EventBus.sendEvent(Event.Toast(error.detail))
                }
            _productsSearch.update {
                it.copy(isLoading = false)
            }
            Log.d("Hello", _productsSearch.value.productsSearch.toString())
        }
    }

}