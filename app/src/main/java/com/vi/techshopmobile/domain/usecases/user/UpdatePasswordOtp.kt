package com.vi.techshopmobile.domain.usecases.user

import arrow.core.Either
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordOtpData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordReponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.user.UserRepository

class UpdatePasswordOtp(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String,updatePasswordOtpData: UpdatePasswordOtpData): Either<ErrorResponse, UpdatePasswordReponse> {
        return userRepository.updatePasswordOtp(email,updatePasswordOtpData)
    }
}