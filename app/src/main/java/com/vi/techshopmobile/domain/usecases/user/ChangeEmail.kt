package com.vi.techshopmobile.domain.usecases.user

import arrow.core.Either
import com.vi.techshopmobile.data.remote.user.dto.ChangeEmailRequest
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordReponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.user.UserRepository

class ChangeEmail (private val userRepository: UserRepository){
    suspend operator fun invoke(  token: String,
                                  changeEmailRequest: ChangeEmailRequest
    ): Either<ErrorResponse, ChangePasswordReponse> {
        return userRepository.changeEmailRequest(token, changeEmailRequest)
    }
}