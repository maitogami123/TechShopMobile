package com.vi.techshopmobile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    val brandName: String,
    val id: Int,
    val products: List<ProductLine>
) : Parcelable