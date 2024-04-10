package com.vi.techshopmobile.presentation.personal_info

import com.vi.techshopmobile.presentation.change_password.ChangePasswordEvent

sealed class PersonalInfoEvent {
    data class GetAllEventPersonalInfo(val token: String) : PersonalInfoEvent()
    data class GetListUserDetail(val token: String) : PersonalInfoEvent()
}