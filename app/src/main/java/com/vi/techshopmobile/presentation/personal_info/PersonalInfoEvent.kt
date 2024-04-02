package com.vi.techshopmobile.presentation.personal_info

sealed class PersonalInfoEvent {
    data class GetAllEventPersonalInfo(val token: String) : PersonalInfoEvent()
    data class GetListUserDetail(val token: String) : PersonalInfoEvent()
}