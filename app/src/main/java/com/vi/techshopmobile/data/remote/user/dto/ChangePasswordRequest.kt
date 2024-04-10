package com.vi.techshopmobile.data.remote.user.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class ChangePasswordRequest(
    val oldpassword : String,
    val password: String,
    val confirmPassword: String
): Parcelable

