package com.vi.techshopmobile.presentation.checkout

import com.vi.techshopmobile.data.remote.orders.dto.RequestCheckOut
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailRequest
import com.vi.techshopmobile.presentation.cart.CartEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.product_details.ProductDetailsEvent

sealed class CheckOutEvent {
    data class GetUserCart(val username: String) : CheckOutEvent()
    data class GetAllEventPersonalInfo(val token: String) : CheckOutEvent()
    data class GetListUserDetail(val token: String) : CheckOutEvent()
    data class CreateOrders(val token: String, val requestCheckOut: RequestCheckOut) :
        CheckOutEvent()

    data class CreateUserDetail(val token: String, val userDetailRequest: UserDetailRequest) :
        CheckOutEvent()

    data class UpdateAllUserDetailsToNotDefault(val id: String, val token: String) : CheckOutEvent()
}