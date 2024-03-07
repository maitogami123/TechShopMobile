package com.vi.techshopmobile.presentation.authenticate.sign_in

import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData

sealed class SignInEvent {
    data class PostLoginRequest(val signInData: SignInData) : SignInEvent()

}