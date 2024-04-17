package com.vi.techshopmobile.presentation.product_details

import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.model.WishItem
import com.vi.techshopmobile.presentation.authenticate.AuthenticateEvent

sealed class ProductDetailsEvent {
    data class GetDetailEvent(val productLine: String) : ProductDetailsEvent()
    data class AddItemToWishListEvent(val wishItem: WishItem) : ProductDetailsEvent()
    data class RemoveItemWishList(val wishItem: WishItem) : ProductDetailsEvent()
    data class SelectItemWishList(val username: String, val productLine: String) : ProductDetailsEvent()
    data class DeleteWishItemByProductLine(val username: String, val productLine: String) : ProductDetailsEvent()
    data class AddItemToCart(val cartItem: CartItem) : ProductDetailsEvent()
    data class IncreaseQuantity(val quantity: Int) : ProductDetailsEvent()
    data class DecreaseQuantity(val quantity: Int) : ProductDetailsEvent()
    data class GetProductsRandom(val categoryName: String, val num: Int): ProductDetailsEvent()
}