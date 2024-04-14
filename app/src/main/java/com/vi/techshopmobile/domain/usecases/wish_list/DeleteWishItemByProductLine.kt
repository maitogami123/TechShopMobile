package com.vi.techshopmobile.domain.usecases.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository
import kotlinx.coroutines.flow.Flow

class DeleteWishItemByProductLine(
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(username: String, productLine: String) {
        wishListRepository.deleteItem(username, productLine)
    }
}