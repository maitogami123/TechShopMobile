package com.vi.techshopmobile.domain.repository.mail

import arrow.core.Either
import com.vi.techshopmobile.data.remote.mail.dto.MailReponse
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.domain.model.ErrorResponse

interface MailRepository {
    suspend fun sendOtpByMail(sendOtpByMailData: SendOtpByMailData): Either<ErrorResponse, MailReponse>
}