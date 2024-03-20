package com.vi.techshopmobile.data.remote.mail.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChangePasswordData(
    val password: String,
    val confirmPassword: String,
    val verificationCode: String,
) : Parcelable