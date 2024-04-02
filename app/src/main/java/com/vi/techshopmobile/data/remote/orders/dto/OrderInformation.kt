package com.vi.techshopmobile.data.remote.orders.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderInformation(
    val address: String,
    val email: String,
    val fullname: String,
    val id: Int,
    val note: String,
    val phoneNumber: String,
    val username: String
) : Parcelable