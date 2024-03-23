package com.vi.techshopmobile.domain.usecases.userDetail

import arrow.core.Either
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.repository.userDetail.UserDetailsRepository

class GetUserDetails(private val userDetailsRepository: UserDetailsRepository) {
    suspend operator fun invoke(token: String): Either<ErrorResponse, UserDetail>{
        return userDetailsRepository.getUserDetail(token)
    }

}