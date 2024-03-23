package com.vi.techshopmobile.domain.repository.orders

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {
    suspend fun getOrders(token: String): Either<ErrorResponse, List<OrderItem>>
}