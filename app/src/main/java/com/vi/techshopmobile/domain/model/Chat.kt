package com.vi.techshopmobile.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chat(
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
): Parcelable