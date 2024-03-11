package com.vi.techshopmobile.domain.repository.category

import com.vi.techshopmobile.domain.model.Category

interface CategoriesRepository {
    suspend fun getCategories(): List<Category>
}