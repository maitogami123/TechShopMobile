package com.vi.techshopmobile.data.remote.user.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChangeEmailRequest(
    val oldEmail :String,
    val email: String,
    val verificationCode: String
):Parcelable