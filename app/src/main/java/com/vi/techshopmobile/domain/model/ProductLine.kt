package com.vi.techshopmobile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductLine(
    val brandName: String,
    val categoryName: String,
    val createdAt: String,
    val deletedAt: String,
    val discount: Double,
    val id: Int,
    val price: Double,
    val productLine: String,
    val productName: String,
    val stock: Int,
    val thumbnailUri: String
) : Parcelable