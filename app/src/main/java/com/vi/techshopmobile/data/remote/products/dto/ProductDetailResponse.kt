package com.vi.techshopmobile.data.remote.products.dto

data class ProductDetailResponse(
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

data class ProductInfo(
    val id: Int,
    val productInformation: String
)