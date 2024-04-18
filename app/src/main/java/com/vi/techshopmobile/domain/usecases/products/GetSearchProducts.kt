package com.vi.techshopmobile.domain.usecases.products

import arrow.core.Either
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.data.remote.products.dto.SearchResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.repository.products.ProductsRepository
import com.vi.techshopmobile.domain.repository.search.SearchRepository

class GetSearchProducts(
    private val productRepository: ProductsRepository
) {
    suspend operator fun invoke(searchProduct: SearchProduct): Either<ErrorResponse, List<ProductLine>> {
        return productRepository.getSearchProducts(searchProduct)
    }
}