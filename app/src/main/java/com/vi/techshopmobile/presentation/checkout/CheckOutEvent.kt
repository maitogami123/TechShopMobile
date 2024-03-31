package com.vi.techshopmobile.presentation.checkout

import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.presentation.cart.CartEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent

sealed class CheckOutEvent {
    data class GetUserCart(val username: String) : CheckOutEvent()
    data class GetAllEventPersonalInfo(val token: String) : CheckOutEvent()
    data class GetListUserDetail(val token: String) : CheckOutEvent()
    data class createOrders(val token: String, val requestCheckOut: RequestCheckOut) :
        CheckOutEvent()
}