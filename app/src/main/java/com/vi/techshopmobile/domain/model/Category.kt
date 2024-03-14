package com.vi.techshopmobile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val brands: List<Brand>,
    val id: Int,
    val name: String,
) : Parcelable

