package com.vi.techshopmobile.presentation.change_password

import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent


open class ChangePasswordEvent {
    data class GetAllEventChangePassword(val token: String): ChangePasswordEvent()


}