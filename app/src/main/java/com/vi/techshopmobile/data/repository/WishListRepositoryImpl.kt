package com.vi.techshopmobile.data.repository

import com.vi.techshopmobile.data.local.WishListDao
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishListRepositoryImpl @Inject constructor(
    private val wishListDao: WishListDao
): WishListRepository {
    override suspend fun upsertArticle(wishItem: WishItem) {
        return wishListDao.upsert(wishItem)
    }

    override suspend fun deleteArticle(wishItem: WishItem) {
        TODO("Not yet implemented")
    }

    override fun getWishList(username: String): Flow<List<WishItem>> {
        return wishListDao.getWishList(username);
    }
}