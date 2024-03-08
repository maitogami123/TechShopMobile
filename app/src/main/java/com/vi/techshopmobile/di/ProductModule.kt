package com.vi.techshopmobile.di

import com.vi.techshopmobile.domain.repository.products.ProductsRepository
import com.vi.techshopmobile.domain.usecases.products.GetProducts
import com.vi.techshopmobile.domain.usecases.products.ProductUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {
    @Provides
    @Singleton
    fun provideProductsUseCases(productsRepository: ProductsRepository) = ProductUseCases(
        getProducts = GetProducts(productsRepository)
    )
}