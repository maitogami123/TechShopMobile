package com.vi.techshopmobile.domain.usecases.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository

class UpsertWishItem (
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(wishItem: WishItem) {
        wishListRepository.upsertArticle(wishItem)
    }
}