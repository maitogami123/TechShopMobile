package com.vi.techshopmobile.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.vi.techshopmobile.data.remote.mail.MailApi
import com.vi.techshopmobile.data.repository.MailRepositoryImpl
import com.vi.techshopmobile.domain.repository.mail.MailRepository
import com.vi.techshopmobile.domain.usecases.mail.SendOtpByMail
import com.vi.techshopmobile.domain.usecases.mail.MailUseCases
import com.vi.techshopmobile.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object MailModule {
    @Provides
    @Singleton
    fun provideMailApi(): MailApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL + "mail/").addConverterFactory(
            GsonConverterFactory.create()
        ).addCallAdapterFactory(EitherCallAdapterFactory.create()).build()
            .create(MailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMailRepository(
        mailApi: MailApi,
    ): MailRepository = MailRepositoryImpl(mailApi)

    @Provides
    @Singleton
    fun provideUserUseCases(
        mailRepository: MailRepository
    ) = MailUseCases(
        sendOtpByMail = SendOtpByMail(mailRepository)
    )
}