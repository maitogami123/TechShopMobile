package com.vi.techshopmobile.presentation.categories

import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ProductLine

data class CategoriesViewState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val categoriesProduct: List<CategoryProduct> = emptyList(),
    val error: String? = null
)

data class CategoryProduct(
    val categoryName: String,
    val products: List<ProductLine>
)