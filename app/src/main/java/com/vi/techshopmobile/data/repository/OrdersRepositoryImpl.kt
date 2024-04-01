package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.OrdersApi
import com.vi.techshopmobile.data.remote.orders.dto.CheckOutResponse
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import com.vi.techshopmobile.domain.repository.orders.OrdersRepository
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    private val ordersApi: OrdersApi
): OrdersRepository{
    override suspend fun getOrders(token: String): Either<ErrorResponse, List<OrderItem>> {
        return ordersApi.getOrders(token)
    }

    override suspend fun createOrder(
        token: String,
        requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, CheckOutResponse> {
        return ordersApi.createOrder(token = token, requestCheckOut = requestCheckOut)

    }
}