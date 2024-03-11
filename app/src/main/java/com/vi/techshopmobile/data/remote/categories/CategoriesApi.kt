package com.vi.techshopmobile.data.remote.categories

import com.vi.techshopmobile.domain.model.Category
import retrofit2.http.GET

interface CategoriesApi {
    @GET("allOfCategoryBrand")
    suspend fun getCategories(): List<Category>
}