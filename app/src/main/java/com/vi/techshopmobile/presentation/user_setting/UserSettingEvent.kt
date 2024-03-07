package com.vi.techshopmobile.presentation.user_setting

sealed class UserSettingEvent {
    object LogoutEvent : UserSettingEvent()
}