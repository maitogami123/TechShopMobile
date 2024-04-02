package com.vi.techshopmobile.domain.repository.orders

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.CheckOutResponse
import com.vi.techshopmobile.data.remote.orders.dto.OrderDetailResponse
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Header
import retrofit2.http.Query

interface OrdersRepository {
    suspend fun getOrders(token: String): Either<ErrorResponse, List<OrderItem>>

    suspend fun getOrderDetail(
        token: String,
        id: String
    ): Either<ErrorResponse, OrderDetailResponse>

    suspend fun createOrder(
        token: String,
        requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, CheckOutResponse>
}