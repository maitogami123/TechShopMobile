package com.vi.techshopmobile.data.repository

import com.vi.techshopmobile.data.local.WishListDao
import com.vi.techshopmobile.data.remote.products.ProductsApi
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.repository.products.ProductsRepository
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishListRepositoryImpl @Inject constructor(
    private val wishListDao: WishListDao
) : WishListRepository {
    override suspend fun upsertArticle(wishItem: WishItem) {
        return wishListDao.upsert(wishItem)
    }

    override suspend fun deleteArticle(wishItem: WishItem) {
        return wishListDao.delete(wishItem)
    }

    override suspend fun deleteItem(username: String, productLine: String) {
        return wishListDao.deleteItem(username, productLine)
    }

    override fun getWishList(username: String): Flow<List<WishItem>> {
        return wishListDao.getWishList(username);
    }

    override fun selectWishItem(
        username: String,
        productLine: String,
    ): Flow<WishItem> {
        return wishListDao.selectWishItem(username, productLine)
    }

    override suspend fun updateWishItem(isSelected: Boolean, id: Int) {
        return wishListDao.updateWishItem(isSelected, id)
    }
}