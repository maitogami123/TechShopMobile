package com.vi.techshopmobile.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.vi.techshopmobile.data.remote.orders.OrdersApi
import com.vi.techshopmobile.data.repository.OrdersRepositoryImpl
import com.vi.techshopmobile.domain.repository.orders.OrdersRepository
import com.vi.techshopmobile.domain.usecases.orders.CreateOrders
import com.vi.techshopmobile.domain.usecases.orders.GetOrders
import com.vi.techshopmobile.domain.usecases.orders.GetOrdersDetail
import com.vi.techshopmobile.domain.usecases.orders.OrdersUseCases
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
object OrderModule {

    @Provides
    @Singleton
    fun provideOrdersApi(): OrdersApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL + "order/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build().create(OrdersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOrdersRepository(
        ordersApi: OrdersApi
    ): OrdersRepository = OrdersRepositoryImpl(ordersApi)

    @Provides
    @Singleton
    fun provideOrdersUseCase(ordersRepository: OrdersRepository) = OrdersUseCases(
        getOrders = GetOrders(ordersRepository),
        createOrders = CreateOrders(ordersRepository),
        getOrdersDetail = GetOrdersDetail(ordersRepository)
    )
}