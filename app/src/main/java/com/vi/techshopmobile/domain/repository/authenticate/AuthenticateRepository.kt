package com.vi.techshopmobile.domain.repository.authenticate

import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticateRepository {
    suspend fun login(signInData: SignInData): SignInResponse;
}