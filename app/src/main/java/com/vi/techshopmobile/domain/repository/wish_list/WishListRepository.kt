package com.vi.techshopmobile.domain.repository.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    suspend fun upsertArticle(wishItem: WishItem)
    suspend fun deleteArticle(wishItem: WishItem)
    suspend fun deleteItem(username: String, productLine: String)
    fun getWishList(username: String): Flow<List<WishItem>>
    fun selectWishItem(username: String, productLine: String): Flow<WishItem>
    suspend fun updateWishItem(isSelected: Boolean, id: Int)
}