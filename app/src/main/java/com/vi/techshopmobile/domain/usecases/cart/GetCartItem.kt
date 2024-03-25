package com.vi.techshopmobile.domain.usecases.cart

import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.repository.cart.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItem(
    private val cartRepository: CartRepository
) {
    operator fun invoke(productLine: String): Flow<CartItem> {
        return cartRepository.getCartItem(productLine)
    }
}