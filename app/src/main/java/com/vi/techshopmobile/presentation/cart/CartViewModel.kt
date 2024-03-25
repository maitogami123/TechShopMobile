package com.vi.techshopmobile.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.presentation.cart.components.CartEvent
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
) : ViewModel() {
    private var _state = MutableStateFlow(emptyList<CartItem>())
    val state = _state.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.GetUserCart -> {
                cartUseCases.getCart(event.username).onEach {
                    _state.value = it.reversed()

                }.launchIn(viewModelScope)
            }

            is CartEvent.DeleteCart -> {
                viewModelScope.launch {
                    cartUseCases.deleteCart(event.cartItem)
                    EventBus.sendEvent(Event.Toast("Đã xóa sản phẩm khỏi giỏ hàng"))
                }
            }
        }

        _state.value.forEach {
                _totalPrice.value += it.price
        }
    }
}
