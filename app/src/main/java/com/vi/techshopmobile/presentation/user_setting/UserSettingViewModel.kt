package com.vi.techshopmobile.presentation.user_setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.presentation.authenticate.sign_in.SignInEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserSettingViewModel @Inject constructor(
    private val appSessionUseCases: AppSessionUseCases,
) : ViewModel() {
    fun onEvent(event: UserSettingEvent) {
        when (event) {
            is UserSettingEvent.LogoutEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    appSessionUseCases.deleteSession()
                }
            }
        }
    }
}