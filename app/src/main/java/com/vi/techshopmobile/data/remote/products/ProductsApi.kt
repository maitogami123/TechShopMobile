package com.vi.techshopmobile.data.remote.products

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import retrofit2.http.GET

interface ProductsApi {
    @GET("all")
    suspend fun getProducts(): Either<ErrorResponse, List<ProductLine>>
}