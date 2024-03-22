package com.vi.techshopmobile.domain.model

data class UserInfo(
    val city: String,
    val createdAt: String,
    val default: Boolean,
    val deletedAt: Any,
    val detailedAddress: String,
    val district: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val modified_at: String,
    val phoneNumber: String
)