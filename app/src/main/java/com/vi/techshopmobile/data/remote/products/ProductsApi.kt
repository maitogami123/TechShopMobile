package com.vi.techshopmobile.data.remote.products

import com.vi.techshopmobile.domain.model.ProductLine
import retrofit2.http.GET

interface ProductsApi {

    @GET("all")
    suspend fun getProducts(): List<ProductLine>
}