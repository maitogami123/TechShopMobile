package com.vi.techshopmobile.presentation.wish_list

import com.vi.techshopmobile.presentation.product_details.ProductDetailsEvent

sealed class WishListEvents {

    data class GetUserWishList(val username: String) : WishListEvents()
    data class GetDetailEvent(val productLine: String) : WishListEvents()
}