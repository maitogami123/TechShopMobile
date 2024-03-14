package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.products.ProductsApi
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.repository.products.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
): ProductsRepository {
    override suspend fun getProducts(): Either<ErrorResponse, List<ProductLine>> {
        return productsApi.getProducts()
       }
}