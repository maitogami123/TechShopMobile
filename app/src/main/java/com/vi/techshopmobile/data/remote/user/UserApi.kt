package com.vi.techshopmobile.data.remote.user

import arrow.core.Either
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordOtpData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordReponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("checkotp?")
    suspend fun checkOtp(@Query("email") email: String, @Query("verificationCode") verificationCode: String): Either<ErrorResponse, String>

    @PATCH("{email}/updatepassword")
    suspend fun updatePasswordOtp(@Path("email") email: String, @Body updatePasswordOtpData: UpdatePasswordOtpData): Either<ErrorResponse, UpdatePasswordReponse>
}