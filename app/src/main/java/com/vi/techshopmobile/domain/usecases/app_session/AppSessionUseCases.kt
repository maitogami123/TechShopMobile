package com.vi.techshopmobile.domain.usecases.app_session

data class AppSessionUseCases (
    val getSession: GetSession,
    val saveSession: SaveSession,
    val checkSession: CheckSession,
    val readSession: ReadSession,
    val deleteSession: DeleteSession
)