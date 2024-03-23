package com.vi.techshopmobile.presentation.personal_info

import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.model.AccountDetail

data class PersonalInfoViewState(
    val isLoading: Boolean = true,
    val userDetail: UserDetail? = UserDetail(
        accountDetail = AccountDetail("", "", true, "", "", "", "", "", 99999, "", "", ""),
        email = "",
        username = ""
    ),
    val error: String? = null
)