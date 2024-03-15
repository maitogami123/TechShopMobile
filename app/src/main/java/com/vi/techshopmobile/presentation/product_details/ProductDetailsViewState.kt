package com.vi.techshopmobile.presentation.product_details

import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.presentation.categories.CategoryProduct

data class ProductDetailsViewState(
    val isLoading: Boolean = false,
    val productDetail: ProductDetailResponse? = null,
    val error: String? = null
)