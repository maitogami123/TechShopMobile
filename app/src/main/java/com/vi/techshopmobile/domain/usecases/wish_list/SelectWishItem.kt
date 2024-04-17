package com.vi.techshopmobile.domain.usecases.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository
import kotlinx.coroutines.flow.Flow

class SelectWishItem(
    private val wishListRepository: WishListRepository
) {
    operator fun invoke(username: String, productLine: String): Flow<WishItem> {
        return wishListRepository.selectWishItem(username, productLine)
    }
}