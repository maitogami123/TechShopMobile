package com.vi.techshopmobile.presentation.change_password

import com.vi.techshopmobile.data.remote.user.dto.ChangePasswordRequest


sealed class ChangePasswordEvent {
    data class GetAllEventChangePassword(
        val token: String,
        val changePasswordRequest: ChangePasswordRequest
    ) : ChangePasswordEvent()

    data class ChangePassword(val token: String, val changePassword: ChangePasswordRequest) :
        ChangePasswordEvent()
}