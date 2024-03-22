package com.vi.techshopmobile.presentation.personal_address

import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent

sealed class PersonalAddressEvent {
    object LogoutEvent : PersonalAddressEvent()

}