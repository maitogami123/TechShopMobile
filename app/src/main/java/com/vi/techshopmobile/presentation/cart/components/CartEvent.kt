package com.vi.techshopmobile.presentation.cart.components

import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.presentation.product_details.ProductDetailsEvent

sealed class CartEvent {
    data class GetUserCart(val username: String) : CartEvent()
    data class DeleteCart(val cartItem: CartItem): CartEvent()
    data class AddItemToCart(val cartItem: CartItem): CartEvent()
    data class IncreaseItemToCart(val cartItem: CartItem): CartEvent()
    data class DecreaseItemToCart(val cartItem: CartItem): CartEvent()
}