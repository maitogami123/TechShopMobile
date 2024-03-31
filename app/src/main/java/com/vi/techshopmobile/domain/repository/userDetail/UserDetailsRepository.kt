package com.vi.techshopmobile.domain.repository.userDetail

import arrow.core.Either
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail

interface UserDetailsRepository {
    suspend fun getUserDetail(token: String): Either<ErrorResponse, UserDetail>
    suspend fun getListUserDetail(token: String): Either<ErrorResponse, List<UserDetail>>
    suspend fun createUserDetail(token: String, userDetailRequest: UserDetailRequest): Either<ErrorResponse, UserDetailResponse>
}