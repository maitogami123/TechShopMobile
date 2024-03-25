package com.vi.techshopmobile.domain.usecases.cart

data class CartUseCases(
    val upsertCart: UpsertCart,
    val deleteCart: DeleteCart,
    val getCart: GetCart
)
