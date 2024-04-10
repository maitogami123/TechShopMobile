package com.vi.techshopmobile.domain.usecases.userDetail

import arrow.core.Either
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.userDetail.UserDetailsRepository

class GetUserDetailById(private val userDetailsRepository: UserDetailsRepository) {
    suspend operator fun invoke(
        token: String,
        id: String
    ): Either<ErrorResponse, UserDetailResponse> {
        return userDetailsRepository.getUserDetailById(token, id)
    }

}