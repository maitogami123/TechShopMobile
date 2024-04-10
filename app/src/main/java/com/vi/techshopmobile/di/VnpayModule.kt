package com.vi.techshopmobile.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.vi.techshopmobile.data.remote.orders.OrdersApi
import com.vi.techshopmobile.data.remote.vnpay.VnPayApi
import com.vi.techshopmobile.data.repository.OrdersRepositoryImpl
import com.vi.techshopmobile.data.repository.VnpayRepositoryImpl
import com.vi.techshopmobile.domain.repository.orders.OrdersRepository
import com.vi.techshopmobile.domain.repository.vnpay.VnPayRepository
import com.vi.techshopmobile.domain.usecases.orders.CreateOrders
import com.vi.techshopmobile.domain.usecases.orders.GetOrders
import com.vi.techshopmobile.domain.usecases.orders.GetOrdersDetail
import com.vi.techshopmobile.domain.usecases.orders.OrdersUseCases
import com.vi.techshopmobile.domain.usecases.vnpay.CreateOrderByVnpay
import com.vi.techshopmobile.domain.usecases.vnpay.VnPayUseCases
import com.vi.techshopmobile.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VnpayModule {

    @Provides
    @Singleton
    fun provideVnPayApi(): VnPayApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL + "api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build().create(VnPayApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVnPayRepository(
        vnPayApi: VnPayApi
    ): VnPayRepository = VnpayRepositoryImpl(vnPayApi)

    @Provides
    @Singleton
    fun provideVnPayUseCase(vnPayRepository: VnPayRepository) = VnPayUseCases(
        CreateOrderByVnpay(vnPayRepository)
    )
}