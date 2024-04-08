package com.vi.techshopmobile.data.remote.brand

import arrow.core.Either
import com.vi.techshopmobile.data.remote.brand.dto.BrandProductResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BrandApi {
    @GET("{categoryName}/{brandName}")
    suspend fun getBrandProduct(
        @Path(
            "categoryName",
            encoded = true
        ) categoryName: String,
        @Path("brandName", encoded = true) brandName: String
    ): Either<ErrorResponse, BrandProductResponse>
}
