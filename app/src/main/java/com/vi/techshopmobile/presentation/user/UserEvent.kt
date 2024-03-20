package com.vi.techshopmobile.presentation.user

import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.data.remote.user.dto.CheckOtpData
import com.vi.techshopmobile.data.remote.user.dto.UpdatePasswordOtpData
import com.vi.techshopmobile.domain.usecases.user.UpdatePasswordOtp
import com.vi.techshopmobile.presentation.mail.MailEvent

sealed class UserEvent {
    data class checkOtp(val email: String, val verificationCode: String): UserEvent()
    data class updatePasswordOtp(val email: String,val updatePasswordOtpData: UpdatePasswordOtpData): UserEvent()
}