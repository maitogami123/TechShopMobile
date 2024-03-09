package com.vi.techshopmobile.domain.usecases.app_session

data class AppSessionUseCases (
    val saveSession: SaveSession,
    val checkSession: CheckSession,
    val readSession: ReadSession,
    val deleteSession: DeleteSession
)