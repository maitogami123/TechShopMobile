package com.vi.techshopmobile.presentation.wish_list

import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.presentation.product_details.ProductDetailsEvent

sealed class WishListEvents {
    data class RemoveItemWishList(val wishItem: WishItem) : WishListEvents()
    data class DeleteItemWishListByProductLine(val username: String, val productLine: String) :
        WishListEvents()

    data class GetUserWishList(val username: String) : WishListEvents()
    data class GetDetailEvent(val productLine: String) : WishListEvents()
}