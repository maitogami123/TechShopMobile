package com.vi.techshopmobile.domain.usecases.mail

import arrow.core.Either
import com.vi.techshopmobile.data.remote.mail.dto.MailReponse
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.mail.MailRepository

class SendOtpByMail(
    private val mailRepository: MailRepository
) {
    suspend operator fun invoke(sendOtpByMailData: SendOtpByMailData): Either<ErrorResponse, MailReponse>{
        return mailRepository.sendOtpByMail(sendOtpByMailData)
    }
}