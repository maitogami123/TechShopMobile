package com.vi.techshopmobile.di

import android.app.Application
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.vi.techshopmobile.data.manager.LocalSessionManagerImpl
import com.vi.techshopmobile.data.remote.authenticate.AuthenticateApi
import com.vi.techshopmobile.data.repository.AuthenticateRepositoryImpl
import com.vi.techshopmobile.domain.manager.LocalSessionManager
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.domain.usecases.app_session.CheckSession
import com.vi.techshopmobile.domain.usecases.app_session.DeleteSession
import com.vi.techshopmobile.domain.usecases.app_session.ReadSession
import com.vi.techshopmobile.domain.usecases.app_session.SaveSession
import com.vi.techshopmobile.domain.usecases.authenticate.AuthenticateUseCases
import com.vi.techshopmobile.domain.usecases.authenticate.SignIn
import com.vi.techshopmobile.domain.usecases.authenticate.SignUp
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
object AuthenticateModule {
    @Provides
    @Singleton
    fun provideAuthenticateApi(): AuthenticateApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL + "auth/").addConverterFactory(
            GsonConverterFactory.create()
        ).addCallAdapterFactory(EitherCallAdapterFactory.create()).build()
            .create(AuthenticateApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalSessionManager(
        application: Application
    ): LocalSessionManager = LocalSessionManagerImpl(application)

    @Provides
    @Singleton
    fun provideAuthenticateRepository(
        authenticateApi: AuthenticateApi,
    ): AuthenticateRepository = AuthenticateRepositoryImpl(authenticateApi)

    @Provides
    @Singleton
    fun provideAppSessionUseCases(
        localSessionManager: LocalSessionManager,
    ) = AppSessionUseCases(
        readSession = ReadSession(localSessionManager),
        saveSession = SaveSession(localSessionManager),
        deleteSession = DeleteSession(localSessionManager),
        checkSession = CheckSession()
    )

    @Provides
    @Singleton
    fun provideAuthenticateUseCases(
        authenticateRepository: AuthenticateRepository
    ) = AuthenticateUseCases(
        signIn = SignIn(authenticateRepository),
        signUp = SignUp(authenticateRepository)
    )
}