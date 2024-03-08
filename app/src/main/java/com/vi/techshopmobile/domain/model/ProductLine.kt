package com.vi.techshopmobile.domain.model

data class ProductLine(
    val brandName: String,
    val categoryName: String,
    val createdAt: String,
    val deletedAt: Any,
    val discount: Double,
    val id: Int,
    val price: Double,
    val productLine: String,
    val productName: String,
    val stock: Int,
    val thumbnailUri: String
)