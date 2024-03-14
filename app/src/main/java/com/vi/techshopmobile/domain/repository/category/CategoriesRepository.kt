package com.vi.techshopmobile.domain.repository.category

import arrow.core.Either
import com.vi.techshopmobile.data.remote.categories.dto.CategoryProductResponse
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine

interface CategoriesRepository {
    suspend fun getCategories(): Either<ErrorResponse, List<Category>>

    suspend fun getCategoryProduct(categoryName: String) : Either<ErrorResponse, CategoryProductResponse>
}