package com.vi.techshopmobile.domain.usecases.app_session

import com.vi.techshopmobile.domain.manager.LocalSessionManager
import kotlinx.coroutines.flow.Flow

class ReadSession(
    private val localSessionManager: LocalSessionManager
) {
    operator fun invoke(): Flow<String> {
        return localSessionManager.readSession()
    }
}