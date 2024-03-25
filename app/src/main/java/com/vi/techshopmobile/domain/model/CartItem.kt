package com.vi.techshopmobile.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey
    val productLine: String,
    val brandName: String,
    val username: String,
    val categoryName: String,
    val price: Double,
    val productName: String,
    val thumbnailUri: String,
    val quantity: Int? = 0
) {
}
