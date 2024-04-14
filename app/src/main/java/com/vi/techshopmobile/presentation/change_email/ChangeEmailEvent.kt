package com.vi.techshopmobile.presentation.change_email

import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.data.remote.user.dto.ChangeEmailRequest
import com.vi.techshopmobile.presentation.forget_password.ForgetPasswordEvent

sealed class ChangeEmailEvent {
    data class GetAllEventChangeEmail(
        val token: String,
        val changeEmailRequest: ChangeEmailRequest): ChangeEmailEvent()
    data class SendOtpByMail(val sendOtpByMailData: SendOtpByMailData): ChangeEmailEvent()
    data class CheckOtp(val email: String, val verificationCode: String): ChangeEmailEvent()
}