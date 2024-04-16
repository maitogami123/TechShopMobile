package com.vi.techshopmobile.di

import android.app.Application
import androidx.room.Room
import com.vi.techshopmobile.data.local.CartDao
import com.vi.techshopmobile.data.local.SearchDAO
import com.vi.techshopmobile.data.local.TechShopDatabase
import com.vi.techshopmobile.data.local.WishListDao
import com.vi.techshopmobile.data.manager.LocalUserManagerImpl
import com.vi.techshopmobile.data.repository.CartRepositoryImpl
import com.vi.techshopmobile.data.repository.SearchRepositoryImpl
import com.vi.techshopmobile.data.repository.WishListRepositoryImpl
import com.vi.techshopmobile.domain.manager.LocalUserManager
import com.vi.techshopmobile.domain.repository.cart.CartRepository
import com.vi.techshopmobile.domain.repository.search.SearchRepository
import com.vi.techshopmobile.domain.repository.wish_list.WishListRepository
import com.vi.techshopmobile.domain.usecases.app_entry.AppEntryUseCases
import com.vi.techshopmobile.domain.usecases.app_entry.ReadAppEntry
import com.vi.techshopmobile.domain.usecases.app_entry.SaveAppEntry
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.domain.usecases.cart.ClearCart
import com.vi.techshopmobile.domain.usecases.cart.DeleteCart
import com.vi.techshopmobile.domain.usecases.cart.GetCart
import com.vi.techshopmobile.domain.usecases.cart.GetCartItem
import com.vi.techshopmobile.domain.usecases.cart.UpdateCart
import com.vi.techshopmobile.domain.usecases.cart.UpsertCart
import com.vi.techshopmobile.domain.usecases.search.GetSearchHistory
import com.vi.techshopmobile.domain.usecases.search.SearchUseCases
import com.vi.techshopmobile.domain.usecases.search.UpsetSearchHistory
import com.vi.techshopmobile.domain.usecases.wish_list.DeleteWishItem
import com.vi.techshopmobile.domain.usecases.wish_list.GetWishList
import com.vi.techshopmobile.domain.usecases.wish_list.UpsertWishItem
import com.vi.techshopmobile.domain.usecases.wish_list.WishListUseCases
import com.vi.techshopmobile.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

    @Provides
    @Singleton
    fun provideTechShopDatabase(
        application: Application
    ): TechShopDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = TechShopDatabase::class.java,
            name = DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWishListDao(
        techShopDatabase: TechShopDatabase
    ): WishListDao = techShopDatabase.wishListDao

    @Provides
    @Singleton
    fun provideWishListRepository(
        wishListDao: WishListDao
    ): WishListRepository = WishListRepositoryImpl(wishListDao)

    @Provides
    @Singleton
    fun provideWishListUseCases(
        wishListRepository: WishListRepository
    ): WishListUseCases = WishListUseCases(
        UpsertWishItem(wishListRepository),
        DeleteWishItem(wishListRepository),
        GetWishList(wishListRepository)
    )

    @Provides
    @Singleton
    fun provideCartDao(
        techShopDatabase: TechShopDatabase
    ): CartDao = techShopDatabase.cartDao

    @Provides
    @Singleton
    fun provideCartRepository(
        cartDao: CartDao
    ): CartRepository = CartRepositoryImpl(cartDao)

    @Provides
    @Singleton
    fun provideCartUseCases(
        cartRepository: CartRepository
    ): CartUseCases = CartUseCases(
        UpsertCart(cartRepository),
        DeleteCart(cartRepository),
        ClearCart(cartRepository),
        GetCart(cartRepository),
        GetCartItem(cartRepository),
        UpdateCart(cartRepository),
    )

    @Provides
    @Singleton
    fun provideSearchDao(
        techShopDatabase: TechShopDatabase
    ): SearchDAO = techShopDatabase.searchDAO

    @Provides
    @Singleton
    fun provideSearchRepository(
        searchDAO: SearchDAO
    ): SearchRepository = SearchRepositoryImpl(searchDAO)

    @Provides
    @Singleton
    fun provideSearchUseCases(searchRepository: SearchRepository): SearchUseCases = SearchUseCases(
        UpsetSearchHistory(searchRepository),
        GetSearchHistory(searchRepository)
    )
}