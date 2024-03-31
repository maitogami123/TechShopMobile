package com.vi.techshopmobile.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.vi.techshopmobile.data.remote.userDetails.UserDetailsApi
import com.vi.techshopmobile.data.repository.UserDetailsRepositoryImpl
import com.vi.techshopmobile.domain.repository.userDetail.UserDetailsRepository
import com.vi.techshopmobile.domain.usecases.userDetail.GetListUserDetail
import com.vi.techshopmobile.domain.usecases.userDetail.GetUserDetails
import com.vi.techshopmobile.domain.usecases.userDetail.UserDetailsUseCases
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
object UserDetailModule {
    @Provides
    @Singleton
    fun provideUserDetailsApi(): UserDetailsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL + "userDetail/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build().create(UserDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDetailsRepository(
        userDetailsApi: UserDetailsApi
    ): UserDetailsRepository = UserDetailsRepositoryImpl(userDetailsApi)


    @Provides
    @Singleton
    fun provideUserDetailsUseCase(userDetailsRepository: UserDetailsRepository) =
        UserDetailsUseCases(
            getUserDetails = GetUserDetails(userDetailsRepository),
            getListUserDetail = GetListUserDetail(userDetailsRepository)
        )
}