package com.vi.techshopmobile.data.remote.products

import com.vi.techshopmobile.domain.model.Product
import com.vi.techshopmobile.util.Constants
import retrofit2.http.GET

interface ProductsApi {

    @GET("product/all")
    suspend fun getProducts(): List<Product>
}