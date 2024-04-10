package com.vi.techshopmobile.data.remote.vnpay

import arrow.core.Either
import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.data.remote.vnpay.dto.VnPayResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface VnPayApi {
    @POST("pay")
    suspend fun paymentByVnPay(
        @Header("Authorization") token: String,
        @Body requestCheckOut: RequestCheckOut
    ): Either<ErrorResponse, VnPayResponse>

    @GET("payment_infor")
    suspend fun transaction(
        @Query("vnp_Amount", encoded = true) amount: String,
        @Query("vnp_BankCode", encoded = true) bankCode: String,
        @Query("vnp_OrderInfo", encoded = true) order: String,
        @Query("vnp_ResponseCode", encoded = true) responseCode: String,
    )
}