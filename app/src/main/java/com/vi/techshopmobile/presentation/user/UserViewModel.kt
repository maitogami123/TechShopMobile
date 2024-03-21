package com.vi.techshopmobile.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.mail.MailUseCases
import com.vi.techshopmobile.domain.usecases.user.UserUseCases
import com.vi.techshopmobile.presentation.mail.MailEvent
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private var _isConfirmOtp = MutableStateFlow(false)
    val isConfirmOtp = _isConfirmOtp.asStateFlow()

    private var _isConfirmDataUpdate = MutableStateFlow(false)
    val isConfirmDataUpdate = _isConfirmDataUpdate.asStateFlow()

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.checkOtp -> {
                viewModelScope.launch {
                    val otpResponse = userUseCases.checkOtp(email = event.email, verificationCode = event.verificationCode)
                    if (otpResponse.isRight()) {
                        otpResponse.onRight {
                            sendEvent(Event.Toast("CheckOtp is $it"))
                            //appSessionUseCases.saveSession(it)
                            _isConfirmOtp.value = true
                        }
                    } else {
                        otpResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                        }
                    }
                }
            }

            is UserEvent.updatePasswordOtp -> {
                viewModelScope.launch {
                    val dataUpdateResponse = userUseCases.updatePasswordOtp(
                        email = event.email,
                        updatePasswordOtpData = event.updatePasswordOtpData
                    )
                    if(dataUpdateResponse.isRight()){
                        dataUpdateResponse.onRight {
                            sendEvent(Event.Toast("Mật khẩu đã được cập nhật"))
                            _isConfirmDataUpdate.value = true
                        }
                    }
                    else{
                        dataUpdateResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                        }
                    }
                }
            }
        }

    }
}