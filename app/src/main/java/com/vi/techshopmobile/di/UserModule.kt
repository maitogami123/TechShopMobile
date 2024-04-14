package com.vi.techshopmobile.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.google.gson.GsonBuilder
import com.vi.techshopmobile.data.remote.user.UserApi
import com.vi.techshopmobile.data.repository.UserRepositoryImpl
import com.vi.techshopmobile.domain.repository.user.UserRepository
import com.vi.techshopmobile.domain.usecases.user.ChangeEmail
import com.vi.techshopmobile.domain.usecases.user.ChangePassword
import com.vi.techshopmobile.domain.usecases.user.CheckOtp
import com.vi.techshopmobile.domain.usecases.user.UpdatePasswordOtp
import com.vi.techshopmobile.domain.usecases.user.UserUseCases
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
object UserModule {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL + "user/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi): UserRepository = UserRepositoryImpl(userApi)

    @Provides
    @Singleton
    fun provideUserUseCases(userRepository: UserRepository) = UserUseCases(
        checkOtp = CheckOtp(userRepository),
        updatePasswordOtp = UpdatePasswordOtp(userRepository),
        changePassword = ChangePassword(userRepository),
        changeEmail = ChangeEmail(userRepository)
    )
}
