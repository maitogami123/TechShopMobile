package com.vi.techshopmobile.domain.repository.user

import arrow.core.Either
import com.vi.techshopmobile.data.remote.user.dto.ChangeEmailRequest
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordReponse
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordRequest
import com.vi.techshopmobile.data.remote.user.dto.CheckOtpData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordOtpData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordReponse
import com.vi.techshopmobile.domain.model.ErrorResponse

interface UserRepository {
    suspend fun checkOtp(email: String, verificationCode: String): Either<ErrorResponse, String>

    suspend fun updatePasswordOtp(
        email: String,
        updatePasswordOtpData: UpdatePasswordOtpData
    ): Either<ErrorResponse, UpdatePasswordReponse>

    suspend fun changePasswordRequest(
        token: String,
        changePasswordRequest: ChangePasswordRequest
    ): Either<ErrorResponse, ChangePasswordReponse>

    suspend fun changeEmailRequest(
        token: String,
        changeEmailRequest: ChangeEmailRequest
    ): Either<ErrorResponse, ChangePasswordReponse>
}