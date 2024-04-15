package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.user.UserApi
import com.vi.techshopmobile.data.remote.user.dto.ChangeEmailRequest
import com.vi.techshopmobile.data.remote.user.dto.ChangeEmailResponse
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordReponse
import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordRequest
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordOtpData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordReponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.user.UserRepository
import retrofit2.http.Query
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun checkOtp(
        @Query("email") email: String,
        @Query("verificationCode") verificationCode: String
    ): Either<ErrorResponse, String> {
        return userApi.checkOtp(email, verificationCode);

    }

    override suspend fun updatePasswordOtp(
        email: String,
        updatePasswordOtpData: UpdatePasswordOtpData
    ): Either<ErrorResponse, UpdatePasswordReponse> {
        return userApi.updatePasswordOtp(email, updatePasswordOtpData);
    }

    override suspend fun changePasswordRequest(
        token: String,
        changePasswordRequest: ChangePasswordRequest
    ): Either<ErrorResponse, ChangePasswordReponse> {
        return userApi.changePassword(token, changePasswordRequest)
    }
    override suspend fun changeEmailRequest(
        token: String,
        changeEmailRequest: ChangeEmailRequest
    ): Either<ErrorResponse, ChangePasswordReponse> {
        return userApi.changeEmail(token, changeEmailRequest )
    }

}