package com.vi.techshopmobile.presentation.authenticate

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.vi.techshopmobile.data.remote.authenticate.dto.SignUpData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.data.remote.authenticate.dto.SignInResult

sealed class AuthenticateEvent {
    data class LoginEvent(val signInData: SignInData) : AuthenticateEvent()
    data class RegisterEvent(val signUpData: SignUpData) : AuthenticateEvent()
}