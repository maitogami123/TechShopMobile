package com.vi.techshopmobile.presentation.authenticate.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository
import com.vi.techshopmobile.domain.usecases.app_entry.AppEntryUseCases
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val appSessionUseCases: AppSessionUseCases,
) : ViewModel() {
    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.PostLoginRequest -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val signInResponse = appSessionUseCases.getSession(event.signInData)
                    appSessionUseCases.saveSession(signInResponse.token)
                }
            }
        }
    }
}