package com.vi.techshopmobile.domain.repository.vnpay

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.data.remote.vnpay.dto.VnPayResponse
import com.vi.techshopmobile.domain.model.ErrorResponse

interface VnPayRepository {
    suspend fun paymentByVnPay(
        token: String,
        requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, VnPayResponse>
}