package com.vi.techshopmobile.data.remote.userDetails

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.model.UserToken
import com.vi.techshopmobile.util.decodeToken
import retrofit2.http.GET
import retrofit2.http.Header

interface UserDetailsApi {
    @GET("default")
    suspend fun getUserDetails(@Header("Authorization") token: String): Either<ErrorResponse, UserDetail>
}