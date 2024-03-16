package com.vi.techshopmobile.domain.usecases.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository
import kotlinx.coroutines.flow.Flow

class GetWishList (
    private val wishListRepository: WishListRepository
) {
    operator fun invoke(username: String): Flow<List<WishItem>> {
        return  wishListRepository.getWishList(username)
    }
}