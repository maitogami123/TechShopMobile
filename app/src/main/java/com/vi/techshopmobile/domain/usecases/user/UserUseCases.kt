package com.vi.techshopmobile.domain.usecases.user

data class UserUseCases(
    val checkOtp: CheckOtp,
    val updatePasswordOtp: UpdatePasswordOtp,
    val changePassword: ChangePassword,
    val changeEmail: ChangeEmail
)