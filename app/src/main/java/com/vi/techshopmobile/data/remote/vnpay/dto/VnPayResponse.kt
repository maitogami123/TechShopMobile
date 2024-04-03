package com.vi.techshopmobile.data.remote.vnpay.dto

data class VnPayResponse(
    val username: String,
    val status: String,
    val orderId: Int,
    val total: Int,
    val paymentStatus: String,
    val message: String,
    val URL: String
)
