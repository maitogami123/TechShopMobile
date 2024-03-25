package com.vi.techshopmobile.presentation.cart.components

import com.vi.techshopmobile.domain.model.CartItem

sealed class CartEvent {
    data class GetUserCart(val username: String) : CartEvent()
    data class DeleteCart(val cartItem: CartItem): CartEvent()
}