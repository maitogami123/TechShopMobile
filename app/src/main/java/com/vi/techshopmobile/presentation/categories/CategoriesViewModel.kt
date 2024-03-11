package com.vi.techshopmobile.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            is CategoriesEvents.getOnEventCategories -> {
                getCategories()
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
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(
                            error = error.error.message
                        )
                    }
                    EventBus.sendEvent(Event.Toast(error.error.message))
                }

            _state.update {
                it.copy(isLoading = false)
            }
            _isLoading.value = false
        }
    }

}