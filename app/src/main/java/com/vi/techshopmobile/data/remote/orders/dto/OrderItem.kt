package com.vi.techshopmobile.data.remote.orders.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderItem(
    val discount: Double,
    val price: Double,
    val productLine: String,
    val productName: String,
    val productSN: String,
    val warrantyDate: String
) : Parcelable