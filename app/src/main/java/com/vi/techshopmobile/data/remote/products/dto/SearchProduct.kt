package com.vi.techshopmobile.data.remote.products.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchProduct(
    val search: String
) : Parcelable