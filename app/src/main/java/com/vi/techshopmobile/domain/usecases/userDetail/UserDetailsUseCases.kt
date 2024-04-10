package com.vi.techshopmobile.domain.usecases.userDetail

data class UserDetailsUseCases(
    val getUserDetails: GetUserDetails,
    val getListUserDetail: GetListUserDetail,
    val createUserDetail: CreateUserDetail,
    val updateAllUserDetailsToNotDefault: UpdateAllUserDetailsToNotDefault,
    val getUserDetailById: GetUserDetailById,
    val updateUserDetail: UpdateUserDetail
)