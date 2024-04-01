package com.vi.techshopmobile.domain.model

data class AccountDetail(
    val city: String,
    val default: Boolean,
    val detailedAddress: String,
    val district: String,
    val email: String,
    val firstName: String,
    var id: Int,
    val lastName: String,
    val phoneNumber: String
)