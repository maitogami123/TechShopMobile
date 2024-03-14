package com.vi.techshopmobile.presentation.categories

sealed class CategoriesEvents {
    object GetAllEventCategories: CategoriesEvents()
    data class GetCategoryProduct(val categoryName: String) : CategoriesEvents()

}