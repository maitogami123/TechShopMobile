package com.vi.techshopmobile.domain.repository.authenticate

import arrow.core.Either
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.AuthResponse
import com.vi.techshopmobile.data.remote.authenticate.dto.SignUpData
import com.vi.techshopmobile.domain.model.ErrorResponse

interface AuthenticateRepository {
    suspend fun login(signInData: SignInData): Either<ErrorResponse, AuthResponse>;

    suspend fun register(signUpData: SignUpData): Either<ErrorResponse, AuthResponse>;
}