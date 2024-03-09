package com.vi.techshopmobile.data.remote.authenticate.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpData (
    val username: String,
    val password: String,
    val confirmPassword: String,
    val email: String
) : Parcelable