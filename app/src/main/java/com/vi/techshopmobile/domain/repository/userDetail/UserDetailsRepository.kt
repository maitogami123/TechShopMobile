package com.vi.techshopmobile.domain.repository.userDetail

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail

interface UserDetailsRepository {
    suspend fun getUserDetail(token: String): Either<ErrorResponse, UserDetail>

}