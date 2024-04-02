package com.vi.techshopmobile.data.remote.orders.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderDetailResponse(
    val orderInformation: OrderInformation,
    val orderItems: List<OrderItem>,
    val orderStatus: String,
    val paymentStatus: String
) : Parcelable