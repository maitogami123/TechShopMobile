package com.vi.techshopmobile.presentation.categories

import com.vi.techshopmobile.presentation.product_details.ProductDetailsEvent

sealed class CategoriesEvents {
    object GetAllEventCategories: CategoriesEvents()
    data class GetCategoryProduct(val categoryName: String) : CategoriesEvents()
    data class GetProductsRandom(val categoryName: String, val num: Int): CategoriesEvents()
}