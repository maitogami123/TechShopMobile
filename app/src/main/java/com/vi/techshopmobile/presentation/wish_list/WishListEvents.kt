package com.vi.techshopmobile.presentation.wish_list

sealed class WishListEvents {

    data class GetUserWishList(val username: String) : WishListEvents()

}