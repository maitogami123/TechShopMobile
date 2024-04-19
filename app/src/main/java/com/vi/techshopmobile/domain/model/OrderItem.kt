package com.vi.techshopmobile.domain.model

data class OrderItem(
    val createdAt: String,
    val deletedAt: String,
    val id: Int,
    val modified_at: String,
    val paymentStatus: String,
    val status: String,
    val total: Double,
    val username: String
)