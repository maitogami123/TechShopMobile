package com.vi.techshopmobile.domain.usecases.app_session

import com.vi.techshopmobile.domain.manager.LocalSessionManager

class SaveSession (
    private val localSessionManager: LocalSessionManager
) {
    suspend operator fun invoke(accessToken: String) {
        localSessionManager.saveSession(accessToken)
    }

}