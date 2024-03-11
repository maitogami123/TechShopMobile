package com.vi.techshopmobile.domain.usecases.categories

import arrow.core.Either
import com.vi.techshopmobile.data.mapper.toNetworkError
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.NetworkError
import com.vi.techshopmobile.domain.repository.category.CategoriesRepository

class GetCategories(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(): Either<NetworkError, List<Category>> {
        return Either.catch {
           categoriesRepository.getCategories()
        }.mapLeft {
            it.toNetworkError()
        }
    }
}