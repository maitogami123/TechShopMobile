package com.vi.techshopmobile.data.remote.orders

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.CheckOutResponse
import com.vi.techshopmobile.data.remote.orders.dto.OrderDetailResponse
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface OrdersApi {
    @GET("getOrder")
    suspend fun getOrders(@Header("Authorization") token: String): Either<ErrorResponse, List<OrderItem>>

    @GET("getOrderDetail")
    suspend fun getOrderDetail(
        @Header("Authorization") token: String,
        @Query(value = "id", encoded = true) id: String
    ): Either<ErrorResponse, OrderDetailResponse>

    @POST("create")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, CheckOutResponse>
}