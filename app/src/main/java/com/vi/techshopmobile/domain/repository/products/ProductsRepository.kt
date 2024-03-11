package com.vi.techshopmobile.domain.repository.products

import com.vi.techshopmobile.domain.model.ProductLine

interface ProductsRepository {
    // Either<return Error, return Success>
    suspend fun getProducts(): List<ProductLine>
}