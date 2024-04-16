package com.vi.techshopmobile.domain.usecases.products

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.repository.products.ProductsRepository

class GetProductsRandom(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(categoryName: String, num: Int): Either<ErrorResponse, List<ProductLine>> {
        return productsRepository.getProductsRandom(categoryName, num)
    }
}