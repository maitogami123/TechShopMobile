package com.vi.techshopmobile.data.remote.user.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckOtpData (
    val email: String,
    val verificationCode: String
): Parcelable