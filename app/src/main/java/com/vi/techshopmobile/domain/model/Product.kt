package com.vi.techshopmobile.domain.model

data class Product(
    val createdAt: String,
    val deletedAt: Any,
    val discount: Double,
    val id: Int,
    val modified_at: String,
    val price: Double,
    val productLine: String,
    val productName: String
)