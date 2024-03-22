package com.vi.techshopmobile.presentation.personal_info

import coil.compose.AsyncImagePainter
import com.vi.techshopmobile.domain.model.UserDetail
import com.vi.techshopmobile.domain.model.UserInfo

data class PersonalInfoViewState(
    val isLoading: Boolean = true,
    val userDetail: UserDetail? = UserDetail(
        userInfo = UserInfo("", "", true, "", "", "", "", "", 99999, "", "", ""),
        email = "",
        username = ""
    ),
    val error: String? = null
)