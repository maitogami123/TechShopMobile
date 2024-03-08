package com.vi.techshopmobile.presentation.products

import com.vi.techshopmobile.domain.model.ProductLine

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<ProductLine> = emptyList(),
    val error: String? = null
) {
}