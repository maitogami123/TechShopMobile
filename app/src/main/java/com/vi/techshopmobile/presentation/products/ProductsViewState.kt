package com.vi.techshopmobile.presentation.products

import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ProductLine

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<ProductLine> = emptyList(),
    val categories : List<Category> = emptyList(),
    val productsOfCategory: List<ProductsOfCategory> = emptyList(),
    val error: String? = null
)


data class ProductsOfCategory(
    val category: String,
    val products: List<ProductLine>
)

