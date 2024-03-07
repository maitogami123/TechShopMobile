package com.vi.techshopmobile.domain.usecases.app_session

import androidx.paging.PagingData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInResponse
import com.vi.techshopmobile.domain.manager.LocalSessionManager
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository
import kotlinx.coroutines.flow.Flow

class GetSession(
    private val authenticateRepository: AuthenticateRepository
) {
    suspend operator fun invoke(signInData: SignInData): SignInResponse {
        return authenticateRepository.login(signInData)
    }
}