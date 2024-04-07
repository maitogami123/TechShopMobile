package com.vi.techshopmobile.domain.usecases.cart

data class CartUseCases(
    val upsertCart: UpsertCart,
    val deleteCart: DeleteCart,
    val clearCart: ClearCart,
    val getCart: GetCart,
    val getCartItem: GetCartItem,
    val updateCart: UpdateCart
)
