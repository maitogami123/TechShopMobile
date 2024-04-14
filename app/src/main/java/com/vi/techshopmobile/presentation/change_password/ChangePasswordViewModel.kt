package com.vi.techshopmobile.presentation.change_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.user.UserUseCases
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
class ChangePasswordViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(PersonalInfoViewState())
    val state = _state.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var _isOldPassword = MutableStateFlow(false)
    val isOldPassword = _isOldPassword.asStateFlow()

    private var _isConfirmDataChange = MutableStateFlow(false)
    val isConfirmDataChange = _isConfirmDataChange.asStateFlow()

    fun onEvent(event: ChangePasswordEvent) {
        when (event) {
            is ChangePasswordEvent.GetAllEventChangePassword -> {
                viewModelScope.launch {
                    val passwordReponse = userUseCases.changePassword(
                        token = "Bearer " + event.token,
                        changePasswordRequest = event.changePasswordRequest
                    )
                    if (passwordReponse.isRight()) {
                        passwordReponse.onRight {
                            _isOldPassword.value = true
                        }
                    } else {
                        passwordReponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                        }

                    }
                }
            }

            is ChangePasswordEvent.ChangePassword -> {
                viewModelScope.launch {
                    val passwordReponse = userUseCases.changePassword(
                        token = "Bearer " + event.token,
                        changePasswordRequest = event.changePassword
                    )
                    if (passwordReponse.isRight()) {
                        passwordReponse.onRight {
                            _isOldPassword.value = true
                            EventBus.sendEvent(Event.Toast("Mật khẩu đã được cập nhật"))
                        }

                    } else {
                        passwordReponse.onLeft {
                            sendEvent(Event.Toast("Mật khẩu cũ không đúng"))
                        }
                    }
                }
            }

        }
    }
}
