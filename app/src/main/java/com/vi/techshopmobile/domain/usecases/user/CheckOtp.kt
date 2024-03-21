package com.vi.techshopmobile.domain.usecases.user

import arrow.core.Either
import com.vi.techshopmobile.data.remote.user.dto.CheckOtpData
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.user.UserRepository

class CheckOtp(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, verificationCode: String): Either<ErrorResponse, String>{
        return userRepository.checkOtp(email, verificationCode)
    }
}