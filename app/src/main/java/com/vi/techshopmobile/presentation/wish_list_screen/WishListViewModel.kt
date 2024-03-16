package com.vi.techshopmobile.presentation.wish_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.usecases.wish_list.WishListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListUseCases: WishListUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<WishItem>())
    val state = _state.asStateFlow()


    fun onEvent(event: WishListEvents) {
        when (event) {
            is WishListEvents.GetUserWishList -> {
                wishListUseCases.getWishList(event.username).onEach {
                    _state.value = it.reversed()
                }.launchIn(viewModelScope)
            }
        }
    }

}