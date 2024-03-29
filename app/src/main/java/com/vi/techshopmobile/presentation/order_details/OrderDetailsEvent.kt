package com.vi.techshopmobile.presentation.order_details

import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent

sealed class OrderDetailsEvent {
    data class GetAllEventPersonalInfo(val token: String): OrderDetailsEvent()
}