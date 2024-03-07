package com.vi.techshopmobile.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalSessionManager {
    // Get access token from the server
    suspend fun getSession() : String
    suspend fun saveSession(accessToken: String)
    // Return access token and maybe some others information too
    fun readSession() : Flow<String>
    suspend fun deleteSession()
    fun checkSession()
}