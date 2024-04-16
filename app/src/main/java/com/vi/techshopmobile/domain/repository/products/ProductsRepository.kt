package com.vi.techshopmobile.domain.repository.products

import arrow.core.Either
import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.data.remote.products.dto.SearchResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine

interface ProductsRepository {
    // Either<return Error, return Success>
    suspend fun getProducts(): Either<ErrorResponse, List<ProductLine>>
    suspend fun getSearchProduct(searchProduct: SearchProduct): Either<ErrorResponse, List<ProductLine>>

    suspend fun getProductDetail(productLine: String): Either<ErrorResponse, ProductDetailResponse>
}