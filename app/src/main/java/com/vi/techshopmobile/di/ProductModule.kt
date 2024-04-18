package com.vi.techshopmobile.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.vi.techshopmobile.data.remote.categories.CategoriesApi
import com.vi.techshopmobile.data.remote.products.ProductsApi
import com.vi.techshopmobile.data.repository.CategoriesRepositoryImpl
import com.vi.techshopmobile.data.repository.ProductsRepositoryImpl
import com.vi.techshopmobile.domain.repository.category.CategoriesRepository
import com.vi.techshopmobile.domain.repository.products.ProductsRepository
import com.vi.techshopmobile.domain.usecases.products.GetProductDetail
import com.vi.techshopmobile.domain.usecases.products.GetProducts
import com.vi.techshopmobile.domain.usecases.products.GetSearchProducts
import com.vi.techshopmobile.domain.usecases.products.GetProductsRandom
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import com.vi.techshopmobile.domain.usecases.search.GetSearchHistory
import com.vi.techshopmobile.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {
    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL + "product/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build().create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        productsApi: ProductsApi
    ): ProductsRepository = ProductsRepositoryImpl(productsApi)

    @Provides
    @Singleton
    fun provideProductsUseCases(productsRepository: ProductsRepository) = ProductUseCases(
        getProducts = GetProducts(productsRepository),
        getProductDetail = GetProductDetail(productsRepository),
        getSearchProducts = GetSearchProducts(productsRepository)
        getProductsRandom = GetProductsRandom(productsRepository)
    )
}