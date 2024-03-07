package com.vi.techshopmobile.data.remote.authenticate.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInData(
    val username: String,
    val password: String
): Parcelable
