package com.vi.techshopmobile.domain.usecases.app_session

import com.vi.techshopmobile.domain.manager.LocalSessionManager

class DeleteSession (
    private val localSessionManager: LocalSessionManager
) {
    suspend operator fun invoke() {
        localSessionManager.deleteSession()
    }
}