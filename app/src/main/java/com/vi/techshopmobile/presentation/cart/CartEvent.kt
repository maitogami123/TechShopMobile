package com.vi.techshopmobile.presentation.cart

import com.vi.techshopmobile.domain.model.CartItem

sealed class CartEvent {
    data class GetUserCart(val username: String) : CartEvent()
    data class DeleteCart(val cartItem: CartItem): CartEvent()
    data class ClearCart(val username: String): CartEvent()
    data class AddItemToCart(val cartItem: CartItem): CartEvent()
    data class IncreaseItemToCart(val cartItem: CartItem): CartEvent()
    data class DecreaseItemToCart(val cartItem: CartItem): CartEvent()
}