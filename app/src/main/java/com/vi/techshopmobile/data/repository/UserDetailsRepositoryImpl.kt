package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.data.remote.userDetails.UserDetailsApi
import com.vi.techshopmobile.data.remote.userDetails.dto.UpdateUserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.repository.userDetail.UserDetailsRepository
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val userDetailsApi: UserDetailsApi
) : UserDetailsRepository {
    override suspend fun getUserDetail(token: String): Either<ErrorResponse, UserDetail> {
        return userDetailsApi.getUserDetails(token = token)
    }

    override suspend fun getListUserDetail(token: String): Either<ErrorResponse, List<UserDetail>> {
        return userDetailsApi.getListUserDetails(token = token)
    }

    override suspend fun getUserDetailById(
        token: String,
        id: String
    ): Either<ErrorResponse, UserDetailResponse> {
        return userDetailsApi.getUserDetailById(token, id)
    }

    override suspend fun createUserDetail(
        token: String,
        userDetailRequest: UserDetailRequest
    ): Either<ErrorResponse, UserDetailResponse> {
        return userDetailsApi.createUserDetails(token, userDetailRequest)
    }

    override suspend fun updateUserDetails(
        token: String,
        id: String,
        updateUserDetailRequest: UpdateUserDetailRequest
    ): Either<ErrorResponse, UserDetailResponse> {
        return userDetailsApi.updateUserDetails(token, id, updateUserDetailRequest)
    }

    override suspend fun updateAllUserDetailsToNotDefault(
        id: String,
        token: String
    ): Either<ErrorResponse, UserDetailResponse> {
        return userDetailsApi.updateAllUserDetailsToNotDefault(id, token)
    }

}