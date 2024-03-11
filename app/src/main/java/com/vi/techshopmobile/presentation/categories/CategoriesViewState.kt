package com.vi.techshopmobile.presentation.categories

import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ProductLine

data class CategoriesViewState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String? = null
)