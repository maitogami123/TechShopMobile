package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.mapper.toNetworkError
import com.vi.techshopmobile.data.remote.products.ProductsApi
import com.vi.techshopmobile.domain.model.NetworkError
import com.vi.techshopmobile.domain.model.Product
import com.vi.techshopmobile.domain.repository.products.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
): ProductsRepository {
    override suspend fun getProducts(): Either<NetworkError, List<Product>> {
        return Either.catch {
            productsApi.getProducts()
        }
            //Map Either<Throwable, List<Product>> inside Either<NetworkError, List<Product>>
            .mapLeft { it.toNetworkError() }
    }
}