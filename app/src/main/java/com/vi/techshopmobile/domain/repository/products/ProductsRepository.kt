package com.vi.techshopmobile.domain.repository.products

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine

interface ProductsRepository {
    // Either<return Error, return Success>
    suspend fun getProducts(): Either<ErrorResponse, List<ProductLine>>
}