package com.vi.techshopmobile.presentation.products
import com.vi.techshopmobile.domain.model.Product

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
) {
}