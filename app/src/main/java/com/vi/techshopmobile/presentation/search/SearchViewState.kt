package com.vi.techshopmobile.presentation.search

import com.vi.techshopmobile.domain.model.ProductLine

data class SearchViewState(
    val isLoading: Boolean = false,
    val productsSearch: List<ProductLine>? = emptyList(),
    val isError: String? = null
)