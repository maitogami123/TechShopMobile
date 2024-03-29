package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.OrdersApi
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
}