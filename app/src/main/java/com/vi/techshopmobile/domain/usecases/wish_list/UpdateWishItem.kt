package com.vi.techshopmobile.domain.usecases.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository
import kotlinx.coroutines.flow.Flow

class UpdateWishItem(
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(isSelected: Boolean, id: Int) {
        wishListRepository.updateWishItem(isSelected, id)
    }
}