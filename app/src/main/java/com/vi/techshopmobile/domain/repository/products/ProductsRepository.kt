package com.vi.techshopmobile.domain.repository.products

import arrow.core.Either
import com.vi.techshopmobile.domain.model.NetworkError
import com.vi.techshopmobile.domain.model.Product

interface ProductsRepository {
    // Either<return Error, return Success>
    suspend fun getProducts(): Either<NetworkError, List<Product>>
}