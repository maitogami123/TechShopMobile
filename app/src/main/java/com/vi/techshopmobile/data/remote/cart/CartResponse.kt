package com.vi.techshopmobile.data.remote.cart

data class CartResponse(
    val price: Double,
    val productLine: String,
    val productName: String,
    val thumbnailUri: String,
    val quantity: Int? = 0,
    val stock: Int? =0
)