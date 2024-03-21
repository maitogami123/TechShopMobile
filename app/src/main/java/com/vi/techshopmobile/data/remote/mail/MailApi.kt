package com.vi.techshopmobile.data.remote.mail

import arrow.core.Either
import com.vi.techshopmobile.data.remote.mail.dto.MailReponse
import com.vi.techshopmobile.data.remote.mail.dto.SendOtpByMailData
import com.vi.techshopmobile.domain.model.ErrorResponse
import retrofit2.http.Body
import retrofit2.http.PATCH

interface MailApi {
    @PATCH("sendmail")
    suspend fun sendOtpByMail(@Body sendOtpByMailData: SendOtpByMailData): Either<ErrorResponse, MailReponse>


}