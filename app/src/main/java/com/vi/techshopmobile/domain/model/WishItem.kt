package com.vi.techshopmobile.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WishItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val productLine: String,
    val productName: String,
    val quantity: Int? = 0
) {
    constructor(username: String, productLine: String, productName: String, quantity: Int? = 0) : this(0, username, productLine, productName, quantity)
}
