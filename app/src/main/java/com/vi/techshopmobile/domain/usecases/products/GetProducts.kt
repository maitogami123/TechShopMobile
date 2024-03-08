package com.vi.techshopmobile.domain.usecases.products

import arrow.core.Either
import com.vi.techshopmobile.data.mapper.toNetworkError
import com.vi.techshopmobile.domain.model.NetworkError
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.repository.products.ProductsRepository

class GetProducts(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): Either<NetworkError,List<ProductLine>>{
        return Either.catch {
            productsRepository.getProducts()
        }
            //Map Either<Throwable, List<Product>> inside Either<NetworkError, List<Product>>
            .mapLeft { it.toNetworkError() }
    }
}