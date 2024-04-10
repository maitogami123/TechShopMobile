package com.vi.techshopmobile.domain.usecases.orders

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.OrderDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.OrderItem
import com.vi.techshopmobile.domain.repository.orders.OrdersRepository

class GetOrdersDetail(
    private val ordersRepository: OrdersRepository
) {
    suspend operator fun invoke(
        token: String,
        id: String
    ): Either<ErrorResponse, OrderDetailResponse> {
        return ordersRepository.getOrderDetail(token, id)
    }
}