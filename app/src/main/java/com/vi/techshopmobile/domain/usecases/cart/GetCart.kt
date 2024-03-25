package com.vi.techshopmobile.domain.usecases.cart

import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.repository.cart.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCart(
    private val cartRepository: CartRepository
) {
    operator fun invoke(username: String): Flow<List<CartItem>> {
        return cartRepository.getCart(username)
    }
}