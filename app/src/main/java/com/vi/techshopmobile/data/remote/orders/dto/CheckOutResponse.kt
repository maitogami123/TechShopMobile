package com.vi.techshopmobile.data.remote.orders.dto

data class CheckOutResponse(
    val id: Int,
    val paymentStatus: String,
    val status: String,
    val total: Int
)