package com.vi.techshopmobile.domain.model

data class Brand(
    val brandName: String,
    val id: Int,
    val products: List<ProductLine>
)