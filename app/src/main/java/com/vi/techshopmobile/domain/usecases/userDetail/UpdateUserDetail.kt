package com.vi.techshopmobile.domain.usecases.userDetail

import arrow.core.Either
import com.vi.techshopmobile.data.remote.userDetails.dto.UpdateUserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.repository.userDetail.UserDetailsRepository

class UpdateUserDetail(private val userDetailsRepository: UserDetailsRepository) {
    suspend operator fun invoke(
        token: String,
        id: String,
        updateUserDetailRequest: UpdateUserDetailRequest
    ): Either<ErrorResponse, UserDetailResponse> {
        return userDetailsRepository.updateUserDetails(token, id, updateUserDetailRequest)
    }
}
