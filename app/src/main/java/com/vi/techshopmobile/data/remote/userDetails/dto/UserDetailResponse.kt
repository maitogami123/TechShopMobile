package com.vi.techshopmobile.data.remote.userDetails.dto

import com.vi.techshopmobile.domain.usecases.userDetail.UpdateAllUserDetailsToNotDefault

data class UserDetailResponse(
    val id: String,
    val city: String,
    val detailedAddress: String,
    val district: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val default: Boolean
)
