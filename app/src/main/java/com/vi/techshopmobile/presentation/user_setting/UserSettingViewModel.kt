package com.vi.techshopmobile.presentation.user_setting

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
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
                    EventBus.sendEvent(Event.Toast("Đã đăng xuất!"))
                }
            }
        }
    }
}