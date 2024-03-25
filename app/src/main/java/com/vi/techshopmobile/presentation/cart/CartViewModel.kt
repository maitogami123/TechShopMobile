package com.vi.techshopmobile.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.presentation.cart.components.CartEvent
import com.vi.techshopmobile.presentation.product_details.ProductDetailsViewState
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
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
                    calculateTotal()
                }.launchIn(viewModelScope)
            }

            is CartEvent.DeleteCart -> {
                viewModelScope.launch {
                    cartUseCases.deleteCart(event.cartItem)
                    calculateTotal()
                    EventBus.sendEvent(Event.Toast("Đã xóa sản phẩm khỏi giỏ hàng"))
                }
            }

            is CartEvent.AddItemToCart -> viewModelScope.launch {
                cartUseCases.getCartItem(event.cartItem.productLine).collect {
                    if (it == null) {
                        cartUseCases.upsertCart(event.cartItem)
                    } else {
                        cartUseCases.upsertCart(event.cartItem.copy(quantity = it.quantity?.plus(event.cartItem.quantity!!)))
                    }
                    EventBus.sendEvent(Event.Toast("Đã thêm vào giỏ hàng"))
                    this.cancel();
                }
            }

            is CartEvent.DecreaseItemToCart -> viewModelScope.launch {
                cartUseCases.getCartItem(event.cartItem.productLine).collect {
                    cartUseCases.upsertCart(event.cartItem.copy(quantity = it.quantity?.minus(1)))
                    this.cancel();
                }
            }

            is CartEvent.IncreaseItemToCart -> viewModelScope.launch {
                cartUseCases.getCartItem(event.cartItem.productLine).collect {
                    cartUseCases.upsertCart(event.cartItem.copy(quantity = it.quantity?.plus(1)))
                    this.cancel();
                }
            }
        }
    }

    fun calculateTotal() {
        var totalPrice = 0.0
        _state.value.forEach {
            totalPrice += (it.price * it.quantity!!)
        }
        _totalPrice.value = totalPrice
    }

}
