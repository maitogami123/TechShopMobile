package com.vi.techshopmobile.presentation.mail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.domain.usecases.mail.MailUseCases
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val mailUseCases: MailUseCases
): ViewModel() {
    private var _isSendMail = MutableStateFlow(false)
    val isSendEmail = _isSendMail.asStateFlow()

    private var _isSendMailLoading = MutableStateFlow(false)
    val isSendEmailLoading = _isSendMailLoading.asStateFlow()

    fun onEvent(event: MailEvent){
        when(event){
            is MailEvent.SendOtpByMail ->{
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