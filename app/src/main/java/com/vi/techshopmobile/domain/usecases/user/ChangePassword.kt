package com.vi.techshopmobile.domain.usecases.user

import arrow.core.Either
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordReponse
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordRequest
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.user.UserRepository

class ChangePassword(private val userRepository: UserRepository) {
    suspend operator fun invoke(  token: String,
                                  changePasswordRequest: ChangePasswordRequest
    ): Either<ErrorResponse, ChangePasswordReponse> {
        return userRepository.changePasswordRequest(token, changePasswordRequest)
    }

}