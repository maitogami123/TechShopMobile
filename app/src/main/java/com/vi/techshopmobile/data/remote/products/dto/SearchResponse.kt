package com.vi.techshopmobile.data.remote.products.dto

import android.os.Parcelable
import com.vi.techshopmobile.domain.model.ProductLine
import kotlinx.parcelize.Parcelize


@Parcelize
data class SearchResponse(
    val products : List<ProductLine>
) : Parcelable