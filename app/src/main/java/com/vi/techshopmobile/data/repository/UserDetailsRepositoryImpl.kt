package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.data.remote.userDetails.UserDetailsApi
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.repository.userDetail.UserDetailsRepository
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val userDetailsApi: UserDetailsApi
): UserDetailsRepository{
    override suspend fun getUserDetail(token: String): Either<ErrorResponse, UserDetail> {
        return userDetailsApi.getUserDetails(token = token)
    }

}