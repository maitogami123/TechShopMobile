package com.vi.techshopmobile.domain.repository.orders

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.CheckOutResponse
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {
    suspend fun getOrders(token: String): Either<ErrorResponse, List<OrderItem>>

    suspend fun createOrder(
        token: String,
        requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, CheckOutResponse>
}