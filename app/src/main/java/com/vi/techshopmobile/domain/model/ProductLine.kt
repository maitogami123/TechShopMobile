package com.vi.techshopmobile.domain.model

data class ProductLine(
    val brandId: Int,
    val brandName: String,
    val categoryId: Int,
    val categoryName: String,
    val imageUris: List<String>,
    val product: Product,
    val productInfos: List<ProductInfo>,
    val stock: Int,
    val thumbnailUri: String,
    val warrantyPeriod: Int,
    val warrantyPeriodId: Int
)