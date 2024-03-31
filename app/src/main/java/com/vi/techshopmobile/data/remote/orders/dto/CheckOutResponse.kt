package com.vi.techshopmobile.data.remote.orders.dto

data class CheckOutResponse(
    val paymentStatus: Any,
    val status: String,
    val total: Int
)