package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.products.ProductsApi
import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
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

    override suspend fun getProductDetail(productLine: String): Either<ErrorResponse, ProductDetailResponse> {
        return productsApi.getProductDetail(productLine)
    }

    override suspend fun getProductsRandom(
        categoryName: String,
        num: Int
    ): Either<ErrorResponse, List<ProductLine>> {
        return  productsApi.getProductsRandom(categoryName, num)
    }
}