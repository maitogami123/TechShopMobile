package com.vi.techshopmobile.data.remote.orders

import arrow.core.Either
import com.vi.techshopmobile.domain.model.Category
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import retrofit2.http.GET
import retrofit2.http.Header

interface OrdersApi {
    @GET("getOrder")
    suspend fun getOrders(@Header("Authorization") token: String): Either<ErrorResponse, List<OrderItem>>

}