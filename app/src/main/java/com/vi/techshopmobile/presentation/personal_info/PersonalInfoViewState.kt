package com.vi.techshopmobile.presentation.personal_info

import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.model.AccountDetail
import com.vi.techshopmobile.presentation.checkout.CheckOutEvent

data class PersonalInfoViewState(
    val isLoading: Boolean = false,
    val listUserDetail: List<UserDetail> = emptyList(),
    val userDetail: UserDetail = UserDetail(
        accountDetail = AccountDetail(
            city = "",
            default = false,
            detailedAddress = "",
            district = "",
            email = "",
            firstName = "",
            id = 0,
            lastName = "",
            phoneNumber = ""
        ),
        email = "",
        username = ""
    ),
    val error: String? = null
)