package com.vi.techshopmobile.data.repository

import com.vi.techshopmobile.data.remote.authenticate.AuthenticateApi
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInResponse
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository

class AuthenticateRepositoryImpl(
    private val authenticateApi: AuthenticateApi
) : AuthenticateRepository {
    override suspend fun login(signInData: SignInData): SignInResponse {
        return authenticateApi.login(signInData)
    }
}