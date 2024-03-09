package com.vi.techshopmobile.presentation.authenticate

import com.vi.techshopmobile.data.remote.authenticate.dto.SignUpData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData

sealed class AuthenticateEvent {
    data class LoginEvent(val signInData: SignInData) : AuthenticateEvent()
    data class RegisterEvent(val signUpData: SignUpData) : AuthenticateEvent()
}