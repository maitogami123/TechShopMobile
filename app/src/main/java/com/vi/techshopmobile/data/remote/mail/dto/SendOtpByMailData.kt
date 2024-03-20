package com.vi.techshopmobile.data.remote.mail.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SendOtpByMailData(
    val email: String
): Parcelable
