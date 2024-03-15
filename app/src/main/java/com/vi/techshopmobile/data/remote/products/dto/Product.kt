package com.vi.techshopmobile.data.remote.products.dto

data class Product(
    val createdAt: String,
    val deletedAt: Any,
    val discount: Double,
    val id: Int,
    val modified_at: Any,
    val price: Double,
    val productLine: String,
    val productName: String
)