package com.vi.techshopmobile.presentation.wish_list_screen

sealed class WishListEvents {

    data class GetUserWishList(val username: String) : WishListEvents()

}