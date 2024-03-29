package com.vi.techshopmobile.domain.usecases.orders

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import com.vi.techshopmobile.domain.repository.orders.OrdersRepository

class GetOrders (
    private val ordersRepository: OrdersRepository
) {
    suspend operator fun invoke(token: String): Either<ErrorResponse,List<OrderItem>>  {
        return  ordersRepository.getOrders(token)
    }
}