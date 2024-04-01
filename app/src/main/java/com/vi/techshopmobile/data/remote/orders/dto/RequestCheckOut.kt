package com.vi.techshopmobile.data.remote.orders.dto

data class RequestCheckOut(
    val cartItems: List<CartItem>,
    val city: String,
    val detailedAddress: String,
    val district: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val note: String? = "",
    val phoneNumber: String,
    val total: Int
)