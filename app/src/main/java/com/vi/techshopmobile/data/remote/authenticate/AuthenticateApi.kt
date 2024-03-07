package com.vi.techshopmobile.data.remote.authenticate

import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticateApi {
    @POST("authenticate")
    suspend fun login(@Body signInData: SignInData) : SignInResponse
}