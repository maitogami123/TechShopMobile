package com.vi.techshopmobile.presentation.authenticate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.some
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.domain.usecases.authenticate.AuthenticateUseCases
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    private val appSessionUseCases: AppSessionUseCases,
    private val authenticateUseCases: AuthenticateUseCases
) : ViewModel() {

    private var _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private var _registerError = MutableStateFlow("");
    val registerError = _registerError.asStateFlow();

    fun onEvent(event: AuthenticateEvent) {
        when (event) {
            is AuthenticateEvent.LoginEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val signInResponse = authenticateUseCases.signIn(event.signInData)
                    if (signInResponse.isRight()) {
                        signInResponse.onRight {
                            appSessionUseCases.saveSession(it.token)
                            _isLoggedIn.value = true
                        }
                    } else {
                        signInResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                        }
                    }
                }
            }

            is AuthenticateEvent.RegisterEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val signInResponse = authenticateUseCases.signUp(event.signUpData)
                    if (signInResponse.isRight()) {
                        signInResponse.onRight {
                            appSessionUseCases.saveSession(it.token)
                            _isLoggedIn.value = true
                        }
                    } else {
                        signInResponse.onLeft {
                            _registerError.value = it.detail
                            sendEvent(Event.Toast("Có lỗi xảy ra!"))

                        }
                    }
                }
            }
        }
    }
}