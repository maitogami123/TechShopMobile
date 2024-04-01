package com.vi.techshopmobile.presentation.order_details

import com.vi.techshopmobile.domain.model.AccountDetail
import com.vi.techshopmobile.domain.model.UserDetail

data class OrderDetailsViewState(
    val isLoading: Boolean = true,
    val userDetail: UserDetail? = UserDetail(
        accountDetail = AccountDetail("", true, "", "", "", "", 0, "", ""),
        email = "",
        username = ""
    ),
    val error: String? = null
)