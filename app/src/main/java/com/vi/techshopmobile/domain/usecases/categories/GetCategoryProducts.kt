package com.vi.techshopmobile.domain.usecases.categories

import arrow.core.Either
import com.vi.techshopmobile.data.mapper.toNetworkError
import com.vi.techshopmobile.data.remote.categories.dto.CategoryProductResponse
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.NetworkError
import com.vi.techshopmobile.domain.repository.category.CategoriesRepository

class GetCategoryProducts (private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(categoryName: String): Either<ErrorResponse, CategoryProductResponse> {
        return categoriesRepository.getCategoryProduct(categoryName)
    }
}