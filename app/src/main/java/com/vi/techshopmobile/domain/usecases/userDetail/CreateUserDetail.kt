package com.vi.techshopmobile.domain.usecases.userDetail

import arrow.core.Either
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.repository.userDetail.UserDetailsRepository

class CreateUserDetail(private val userDetailsRepository: UserDetailsRepository) {
    suspend operator fun invoke(
        token: String,
        userDetailRequest: UserDetailRequest
    ): Either<ErrorResponse, UserDetailResponse> {
        return userDetailsRepository.createUserDetail(token, userDetailRequest)
    }
}
