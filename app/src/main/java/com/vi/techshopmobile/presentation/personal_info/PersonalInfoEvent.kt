package com.vi.techshopmobile.presentation.personal_info

import com.vi.techshopmobile.data.remote.userDetails.dto.UpdateUserDetailRequest
import com.vi.techshopmobile.presentation.change_password.ChangePasswordEvent
import com.vi.techshopmobile.presentation.checkout.CheckOutEvent

sealed class PersonalInfoEvent {
    data class GetAllEventPersonalInfo(val token: String) : PersonalInfoEvent()
    data class GetListUserDetail(val token: String) : PersonalInfoEvent()
    data class UpdateUserDetail(
        val token: String,
        val id: String,
        val updateUserDetailRequest: UpdateUserDetailRequest
    ) : PersonalInfoEvent()
}