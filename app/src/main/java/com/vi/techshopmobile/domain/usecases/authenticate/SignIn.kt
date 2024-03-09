package com.vi.techshopmobile.domain.usecases.authenticate

import arrow.core.Either
import com.vi.techshopmobile.data.mapper.toNetworkError
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.AuthResponse
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.NetworkError
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository

class SignIn(
    private val authenticateRepository: AuthenticateRepository
) {
    suspend operator fun invoke(signInData: SignInData): Either<ErrorResponse, AuthResponse> {
        return authenticateRepository.login(signInData)
    }
}