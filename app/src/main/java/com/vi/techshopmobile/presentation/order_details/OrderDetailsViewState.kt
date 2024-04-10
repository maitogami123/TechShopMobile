package com.vi.techshopmobile.presentation.order_details

import com.vi.techshopmobile.data.remote.orders.dto.OrderDetailResponse
import com.vi.techshopmobile.data.remote.userDetails.dto.UserDetailResponse
import com.vi.techshopmobile.domain.model.AccountDetail
import com.vi.techshopmobile.domain.model.UserDetail

data class OrderDetailsViewState(
    val isLoading: Boolean = true,
    val orderDetailResponse: OrderDetailResponse? = null,
    val userDetail: UserDetail? = UserDetail(
        accountDetail = AccountDetail(
            "", true, "", "", "", "", 0, "", ""
        ),
        email = "",
        username = ""
    ),
    val error: String? = null
)