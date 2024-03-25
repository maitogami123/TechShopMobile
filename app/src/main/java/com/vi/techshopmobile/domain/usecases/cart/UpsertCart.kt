package com.vi.techshopmobile.domain.usecases.cart

import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.repository.cart.CartRepository

class UpsertCart(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartItem){
        cartRepository.upsertCart(cartItem)
    }
}