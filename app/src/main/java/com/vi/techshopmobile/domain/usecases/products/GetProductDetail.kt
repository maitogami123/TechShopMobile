package com.vi.techshopmobile.domain.usecases.products

import arrow.core.Either
import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.repository.products.ProductsRepository

class GetProductDetail(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(productLine: String): Either<ErrorResponse, ProductDetailResponse> {
        return productsRepository.getProductDetail(productLine)
    }
}