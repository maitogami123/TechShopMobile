package com.vi.techshopmobile.domain.repository.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    suspend fun upsertArticle(wishItem: WishItem)
    suspend fun deleteArticle(wishItem: WishItem)
    fun getWishList(username: String): Flow<List<WishItem>>
}