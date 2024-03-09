package com.vi.techshopmobile.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.vi.techshopmobile.domain.manager.LocalSessionManager
import com.vi.techshopmobile.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalSessionManagerImpl(
    private val context: Context
) : LocalSessionManager {

    override suspend fun saveSession(accessToken: String) {
        context.dataStore.edit {settings ->
            settings[PreferencesKeys.APP_SESSION] = accessToken
        }
    }

    override fun readSession(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_SESSION] ?: ""
        }
    }

    override suspend fun deleteSession() {
        context.dataStore.edit {settings ->
            settings[PreferencesKeys.APP_SESSION] = ""
        }
    }

    override fun checkSession() {
        TODO("Not yet implemented")
    }

}

