package com.vi.techshopmobile.data.remote.userDetails.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateUserDetailRequest(
    val city: String?,
    val detailedAddress: String?,
    val district: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: String?
) : Parcelable