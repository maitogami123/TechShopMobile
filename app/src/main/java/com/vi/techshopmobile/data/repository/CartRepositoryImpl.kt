package com.vi.techshopmobile.data.repository

import com.vi.techshopmobile.data.local.CartDao
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.repository.cart.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
): CartRepository{
    override suspend fun upsertCart(cartItem: CartItem) {
        return cartDao.upsert(cartItem)
    }

    override suspend fun deleteCart(cartItem: CartItem) {
        return cartDao.delete(cartItem)
    }

    override fun getCart(username: String): Flow<List<CartItem>> {
        return cartDao.getCart(username)
    }

}