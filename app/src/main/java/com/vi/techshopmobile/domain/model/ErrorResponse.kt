package com.vi.techshopmobile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val detail: String,
    val instance: String,
    val status: Int,
    val title: String,
    val type: String
) : Parcelable