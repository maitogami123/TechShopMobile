package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.authenticate.AuthenticateApi
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.AuthResponse
import com.vi.techshopmobile.data.remote.authenticate.dto.SignUpData
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository

class AuthenticateRepositoryImpl(
    private val authenticateApi: AuthenticateApi
) : AuthenticateRepository {
    override suspend fun login(signInData: SignInData): Either<ErrorResponse, AuthResponse> {
        return authenticateApi.login(signInData)
    }

    override suspend fun register(signUpData: SignUpData): Either<ErrorResponse, AuthResponse> {
        return authenticateApi.register(signUpData)
    }
}