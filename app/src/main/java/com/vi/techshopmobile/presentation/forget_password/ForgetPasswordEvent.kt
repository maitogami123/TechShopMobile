package com.vi.techshopmobile.presentation.forget_password

import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordOtpData

sealed class ForgetPasswordEvent {
    data class SendOtpByMail(val sendOtpByMailData: SendOtpByMailData): ForgetPasswordEvent()
    data class CheckOtp(val email: String, val verificationCode: String): ForgetPasswordEvent()
    data class UpdatePasswordOtp(val email: String, val updatePasswordOtpData: UpdatePasswordOtpData): ForgetPasswordEvent()
}