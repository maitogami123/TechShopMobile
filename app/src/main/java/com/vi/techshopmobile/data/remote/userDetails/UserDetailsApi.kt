package com.vi.techshopmobile.data.remote.userDetails

import arrow.core.Either
import com.vi.techshopmobile.data.remote.userDetails.dto.UpdateUserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.model.UserToken
import com.vi.techshopmobile.util.decodeToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserDetailsApi {
    @GET("all")
    suspend fun getListUserDetails(@Header("Authorization") token: String): Either<ErrorResponse, List<UserDetail>>
    @GET("{id}")
    suspend fun getUserDetailById(@Header("Authorization") token: String, @Path("id") id: String): Either<ErrorResponse, UserDetailResponse>

    @GET("default")
    suspend fun getUserDetails(@Header("Authorization") token: String): Either<ErrorResponse, UserDetail>

    @POST("create")
    suspend fun createUserDetails(
        @Header("Authorization") token: String,
        @Body userDetailRequest: UserDetailRequest
    ): Either<ErrorResponse, UserDetailResponse>
    @PATCH("{id}/update")
    suspend fun updateUserDetails(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body updateUserDetailRequest: UpdateUserDetailRequest
    ): Either<ErrorResponse, UserDetailResponse>

    @PATCH("{id}/active")
    suspend fun updateAllUserDetailsToNotDefault(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Either<ErrorResponse, UserDetailResponse>
}