package com.vi.techshopmobile.domain.usecases.vnpay

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.CheckOutResponse
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.data.remote.vnpay.dto.VnPayResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.vnpay.VnPayRepository

class CreateOrderByVnpay(private val vnPayRepository: VnPayRepository) {
    suspend operator fun invoke(token: String, requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, VnPayResponse> {
        return vnPayRepository.paymentByVnPay(token = token, requestCheckOut = requestCheckOut)
    }
}