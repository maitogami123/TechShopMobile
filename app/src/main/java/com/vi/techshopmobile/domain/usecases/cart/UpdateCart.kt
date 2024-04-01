package com.vi.techshopmobile.domain.usecases.cart

import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.repository.cart.CartRepository

class UpdateCart(private val cartRepository: CartRepository) {
    suspend operator fun invoke(quantity: Int, id: Int){
        cartRepository.updateCart(quantity, id)
    }
}