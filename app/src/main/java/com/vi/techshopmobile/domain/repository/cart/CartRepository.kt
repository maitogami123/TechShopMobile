package com.vi.techshopmobile.domain.repository.cart

import com.vi.techshopmobile.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun upsertCart(cartItem: CartItem)
    suspend fun deleteCart(cartItem: CartItem)
    fun getCart(username: String): Flow<List<CartItem>>

    fun getCartItem(productLine: String): Flow<CartItem>
}