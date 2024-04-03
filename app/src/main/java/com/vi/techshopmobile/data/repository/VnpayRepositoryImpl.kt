package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.data.remote.vnpay.VnPayApi
import com.vi.techshopmobile.data.remote.vnpay.dto.VnPayResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.vnpay.VnPayRepository
import retrofit2.http.Body

class VnpayRepositoryImpl(private val vnPayApi: VnPayApi) : VnPayRepository {
    override suspend fun paymentByVnPay(
        token: String,
        requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, VnPayResponse> {
        return vnPayApi.paymentByVnPay(token, requestCheckOut)
    }

}