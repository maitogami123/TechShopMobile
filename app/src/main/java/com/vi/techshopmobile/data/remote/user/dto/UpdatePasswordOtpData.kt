package com.vi.techshopmobile.data.remote.user.dto

data class UpdatePasswordOtpData (
    val password: String,
    val confirmPassword: String,
    val verificationCode: String,
)