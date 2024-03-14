package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.categories.CategoriesApi
import com.vi.techshopmobile.data.remote.categories.dto.CategoryProductResponse
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.repository.category.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesApi: CategoriesApi
): CategoriesRepository {
    override suspend fun getCategories(): Either<ErrorResponse, List<Category>> {
        return categoriesApi.getCategories()
    }

    override suspend fun getCategoryProduct(categoryName: String): Either<ErrorResponse, CategoryProductResponse> {
        return categoriesApi.getCategoryProduct(categoryName)
    }

}