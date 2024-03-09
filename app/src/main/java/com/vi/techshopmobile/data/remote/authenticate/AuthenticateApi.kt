package com.vi.techshopmobile.data.remote.authenticate

import arrow.core.Either
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.AuthResponse
import com.vi.techshopmobile.data.remote.authenticate.dto.SignUpData
import com.vi.techshopmobile.domain.model.ErrorResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticateApi {
    @POST("authenticate")
    suspend fun login(@Body signInData: SignInData) : Either<ErrorResponse, AuthResponse>

    @POST("register")
    suspend fun register(@Body signUpData: SignUpData) : Either<ErrorResponse, AuthResponse>
}