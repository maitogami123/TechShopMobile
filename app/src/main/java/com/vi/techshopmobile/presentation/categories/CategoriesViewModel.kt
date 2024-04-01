package com.vi.techshopmobile.presentation.categories

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.usecases.categories.CategoriesUseCases
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCases: CategoriesUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesViewState())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getCategories()
    }

    fun onEvent(event: CategoriesEvents) {
       when (event) {
            is CategoriesEvents.GetAllEventCategories -> {
                getCategories()
            }
            is CategoriesEvents.GetCategoryProduct -> {
                getProductCategory(event.categoryName)
            }
       }
    }
    fun getCategories(){
        viewModelScope.launch {
            _isLoading.value = true
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            categoriesUseCases.getCategories()
                .onRight { categories ->
                    _state.update {
                        it.copy(
                            categories = categories
                        )
                    }
                    for (category in categories) {
                        getProductCategory(category.name)
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
            _isLoading.value = false
        }
    }

    fun getProductCategory(categoryName: String) {
        viewModelScope.launch {
            categoriesUseCases.getCategoryProducts(categoryName)
                .onRight { response ->
                    _state.update {categoriesViewState ->
                        categoriesViewState.copy(
                            categoriesProduct = categoriesViewState.categoriesProduct + CategoryProduct(response.name, response.products)
                        )
                    }
                }
                .onLeft { error ->
                    EventBus.sendEvent(Event.Toast(error.detail))
                }
        }
    }

}