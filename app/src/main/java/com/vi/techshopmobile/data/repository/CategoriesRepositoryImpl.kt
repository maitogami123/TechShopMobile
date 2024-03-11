package com.vi.techshopmobile.data.repository

import com.vi.techshopmobile.data.remote.categories.CategoriesApi
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.repository.category.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesApi: CategoriesApi
): CategoriesRepository {
    override suspend fun getCategories(): List<Category> {
        return categoriesApi.getCategories()
    }

}