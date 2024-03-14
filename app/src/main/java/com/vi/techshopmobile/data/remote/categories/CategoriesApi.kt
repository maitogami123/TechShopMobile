package com.vi.techshopmobile.data.remote.categories

import arrow.core.Either
import com.vi.techshopmobile.data.remote.categories.dto.CategoryProductResponse
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesApi {
    @GET("allOfCategoryBrand")
    suspend fun getCategories(): Either<ErrorResponse, List<Category>>

    @GET("{categoryName}")
    suspend fun getCategoryProduct(@Path("categoryName", encoded = true) categoryName: String): Either<ErrorResponse, CategoryProductResponse>
}