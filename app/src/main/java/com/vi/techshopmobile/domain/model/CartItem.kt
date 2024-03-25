package com.vi.techshopmobile.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brandName: String,
    val username: String,
    val categoryName: String,
    val price: Double,
    val productLine: String,
    val productName: String,
    val thumbnailUri: String,
    val quantity: Int? = 0
) {
    constructor(
        brandName: String,
        username: String,
        categoryName: String,
        price: Double,
        productLine: String,
        productName: String,
        thumbnailUri: String,
        quantity: Int? = 0
    ) : this(
        0,
        brandName,
        username,
        categoryName,
        price,
        productLine,
        productName,
        thumbnailUri,
        quantity
    )
}
