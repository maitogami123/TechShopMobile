package com.vi.techshopmobile.domain.usecases.authenticate

import arrow.core.Either
import com.vi.techshopmobile.data.mapper.toNetworkError
import com.vi.techshopmobile.data.remote.authenticate.dto.AuthResponse
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignUpData
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.NetworkError
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository

class SignUp (
    private val authenticateRepository: AuthenticateRepository
) {
    suspend operator fun invoke(signUpData: SignUpData): Either<ErrorResponse, AuthResponse> {
        return authenticateRepository.register(signUpData)
    }
}