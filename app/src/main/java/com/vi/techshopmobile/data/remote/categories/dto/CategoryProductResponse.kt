package com.vi.techshopmobile.data.remote.categories.dto

import android.os.Parcelable
import com.vi.techshopmobile.domain.model.ProductLine
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryProductResponse(
    val id: Int,
    val name: String,
    val products: List<ProductLine>
) : Parcelable