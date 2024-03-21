package com.vi.techshopmobile.presentation.mail

import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData

sealed class MailEvent{
    data class SendOtpByMail(val sendOtpByMailData: SendOtpByMailData): MailEvent()
}