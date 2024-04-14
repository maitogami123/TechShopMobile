package com.vi.techshopmobile.presentation.change_email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.mail.MailUseCases
import com.vi.techshopmobile.domain.usecases.user.UserUseCases
import com.vi.techshopmobile.presentation.forget_password.ForgetPasswordEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewState
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val mailUseCases: MailUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(PersonalInfoViewState())
    val state = _state.asStateFlow()

    //
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    //kiem tra email, thay doi email
    private var _isOldEmail = MutableStateFlow(false)
    val isOldEmail = _isOldEmail.asStateFlow()

    //gui email
    private var _isSendMail = MutableStateFlow(false)
    val isSendEmail = _isSendMail.asStateFlow()
    private var _isSendMailLoading = MutableStateFlow(false)
    val isSendEmailLoading = _isSendMailLoading.asStateFlow()

    // check otp
    private var _isConfirmOtp = MutableStateFlow(false)
    val isConfirmOtp = _isConfirmOtp.asStateFlow()


    //Thay doi email
    fun onEvent(event: ChangeEmailEvent) {
        when (event) {


            is ChangeEmailEvent.GetAllEventChangeEmail -> {
                viewModelScope.launch {
                    val emailReponse = userUseCases.changeEmail(
                        token = "Bearer " + event.token,
                        changeEmailRequest = event.changeEmailRequest
                    )
                    if (emailReponse.isRight()) {
                        emailReponse.onRight {
                            _isOldEmail.value = true
                            EventBus.sendEvent(Event.Toast("Email đã được cập nhật"))
                        }
                    } else {
                        emailReponse.onLeft {
                            _isOldEmail.value = false
                            EventBus.sendEvent(Event.Toast(it.detail))
                        }

                    }
                }
            }
            //check otp
            is ChangeEmailEvent.CheckOtp -> {
                viewModelScope.launch {
                    val otpResponse = userUseCases.checkOtp(
                        email = event.email,
                        verificationCode = event.verificationCode
                    )
                    if (otpResponse.isRight()) {
                        otpResponse.onRight {
                            sendEvent(Event.Toast("CheckOtp is $it"))
                            _isConfirmOtp.value = true
                        }
                    } else {
                        otpResponse.onLeft {
                            _isConfirmOtp.value = false
                            sendEvent(Event.Toast("OTP không hợp lệ"))
                        }
                    }
                }
            }


//send email
            is ChangeEmailEvent.SendOtpByMail -> {
                viewModelScope.launch {
                    _isSendMailLoading.value = true
                    val mailResponse = mailUseCases.sendOtpByMail(event.sendOtpByMailData)
                    if (mailResponse.isRight()) {
                        mailResponse.onRight {
                            sendEvent(Event.Toast("Đã gửi mã thành công"))
                            _isSendMail.value = true
                        }
                    } else {
                        mailResponse.onLeft {
                            sendEvent(Event.Toast("Email không tồn tại"))
                            _isSendMailLoading.value = false
                        }
                    }
                }
            }
        }
    }
}
