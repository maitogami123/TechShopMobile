package com.vi.techshopmobile.data.remote.brand.dto

import android.os.Parcelable
import com.vi.techshopmobile.domain.model.ProductLine
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandProductResponse (
    val id: Int,
    val name: String,
    val category: String,
    val products: List<ProductLine>
):Parcelable