package com.vi.techshopmobile.domain.usecases.orders

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.CheckOutResponse
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.orders.OrdersRepository

class CreateOrders(
    private val ordersRepository: OrdersRepository
) {
    suspend operator fun invoke(
        token: String, requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, CheckOutResponse> {
        return ordersRepository.createOrder(token = token, requestCheckOut = requestCheckOut)
    }
}