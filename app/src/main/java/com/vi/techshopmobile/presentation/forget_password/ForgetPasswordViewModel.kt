package com.vi.techshopmobile.presentation.forget_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.mail.MailUseCases
import com.vi.techshopmobile.domain.usecases.user.UserUseCases
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val mailUseCases: MailUseCases
) : ViewModel() {
    private var _isConfirmOtp = MutableStateFlow(false)
    val isConfirmOtp = _isConfirmOtp.asStateFlow()

    private var _isConfirmDataUpdate = MutableStateFlow(false)
    val isConfirmDataUpdate = _isConfirmDataUpdate.asStateFlow()

    private var _isSendMail = MutableStateFlow(false)
    val isSendEmail = _isSendMail.asStateFlow()

    private var _isSendMailLoading = MutableStateFlow(false)
    val isSendEmailLoading = _isSendMailLoading.asStateFlow()

    fun onEvent(event: ForgetPasswordEvent) {
        when (event) {
            is ForgetPasswordEvent.CheckOtp -> {
                viewModelScope.launch {
                    val otpResponse = userUseCases.checkOtp(email = event.email, verificationCode = event.verificationCode)
                    if (otpResponse.isRight()) {
                        otpResponse.onRight {
                            // Todo: Change this toast message to something else
                            sendEvent(Event.Toast("CheckOtp is $it"))
                            _isConfirmOtp.value = true
                        }
                    } else {
                        otpResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                        }
                    }
                }
            }

            is ForgetPasswordEvent.UpdatePasswordOtp -> {
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

            is ForgetPasswordEvent.SendOtpByMail -> {
                viewModelScope.launch {
                    _isSendMailLoading.value = true
                    val mailResponse = mailUseCases.sendOtpByMail(event.sendOtpByMailData)
                    if(mailResponse.isRight()){
                        mailResponse.onRight {
                            sendEvent(Event.Toast("Đã gửi mã thành công"))
                            _isSendMail.value = true
                        }
                    } else{
                        mailResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                            _isSendMailLoading.value = false
                        }
                    }
                }
            }

        }

    }
}