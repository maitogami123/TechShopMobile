package com.vi.techshopmobile.domain.usecases.app_entry

import com.vi.techshopmobile.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}