package com.vi.techshopmobile.di

import android.app.Application
import com.vi.techshopmobile.data.manager.LocalSessionManagerImpl
import com.vi.techshopmobile.data.manager.LocalUserManagerImpl
import com.vi.techshopmobile.data.remote.authenticate.AuthenticateApi
import com.vi.techshopmobile.data.remote.categories.CategoriesApi
import com.vi.techshopmobile.data.remote.products.ProductsApi
import com.vi.techshopmobile.data.repository.AuthenticateRepositoryImpl
import com.vi.techshopmobile.domain.manager.LocalSessionManager
import com.vi.techshopmobile.domain.manager.LocalUserManager
import com.vi.techshopmobile.domain.repository.authenticate.AuthenticateRepository
import com.vi.techshopmobile.domain.usecases.app_entry.AppEntryUseCases
import com.vi.techshopmobile.domain.usecases.app_entry.ReadAppEntry
import com.vi.techshopmobile.domain.usecases.app_entry.SaveAppEntry
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.domain.usecases.app_session.CheckSession
import com.vi.techshopmobile.domain.usecases.app_session.DeleteSession
import com.vi.techshopmobile.domain.usecases.app_session.ReadSession
import com.vi.techshopmobile.domain.usecases.app_session.SaveSession
import com.vi.techshopmobile.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    //products

}