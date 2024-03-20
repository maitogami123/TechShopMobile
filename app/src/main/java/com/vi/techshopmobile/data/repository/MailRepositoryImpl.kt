package com.vi.techshopmobile.data.repository

import arrow.core.Either
import com.vi.techshopmobile.data.remote.mail.MailApi
import com.vi.techshopmobile.data.remote.mail.dto.MailReponse
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.repository.mail.MailRepository
import javax.inject.Inject

class MailRepositoryImpl @Inject constructor(
    private val mailApi: MailApi
) : MailRepository {
    override suspend fun sendOtpByMail(sendOtpByMailData: SendOtpByMailData): Either<ErrorResponse, MailReponse> {
       return mailApi.sendOtpByMail(sendOtpByMailData)
    }

}