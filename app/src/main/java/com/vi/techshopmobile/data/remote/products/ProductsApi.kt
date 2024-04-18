package com.vi.techshopmobile.data.remote.products

import arrow.core.Either
import com.vi.techshopmobile.data.remote.products.dto.ProductDetailResponse
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.data.remote.products.dto.SearchResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {
    @GET("all")
    suspend fun getProducts(): Either<ErrorResponse, List<ProductLine>>

    @GET("{productLine}")
    suspend fun getProductDetail(
        @Path(
            "productLine",
            encoded = true
        ) productLine: String
    ): Either<ErrorResponse, ProductDetailResponse>

    @POST("search")
    suspend fun getSearchProducts(
        @Body searchProduct: SearchProduct
    ): Either<ErrorResponse, List<ProductLine>>
  
    @GET("randomProduct/{categoryName}")
    suspend fun getProductsRandom(
        @Path("categoryName", encoded = true) categoryName: String,
        @Query("num", encoded = true) num: Int
    ): Either<ErrorResponse, List<ProductLine>>

}