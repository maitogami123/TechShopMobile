package com.vi.techshopmobile.di

import android.content.ContentValues.TAG
import android.util.Log
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.vi.techshopmobile.data.remote.categories.CategoriesApi
import com.vi.techshopmobile.data.repository.CategoriesRepositoryImpl
import com.vi.techshopmobile.domain.repository.category.CategoriesRepository
import com.vi.techshopmobile.domain.usecases.categories.CategoriesUseCases
import com.vi.techshopmobile.domain.usecases.categories.GetCategories
import com.vi.techshopmobile.domain.usecases.categories.GetCategoryProducts
import com.vi.techshopmobile.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Provides
    @Singleton
    fun provideCategoriesApi(): CategoriesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL + "category/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build().create(CategoriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        categoriesApi: CategoriesApi
    ) : CategoriesRepository = CategoriesRepositoryImpl(categoriesApi)


    @Provides
    @Singleton
    fun provideCategoriesUseCase(categoriesRepository: CategoriesRepository) = CategoriesUseCases(
        getCategories = GetCategories(categoriesRepository),
        getCategoryProducts = GetCategoryProducts(categoriesRepository)
    )
}