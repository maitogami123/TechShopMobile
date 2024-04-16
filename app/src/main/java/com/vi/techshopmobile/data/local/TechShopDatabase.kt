package com.vi.techshopmobile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.domain.model.WishItem

@Database(entities = [WishItem::class, CartItem::class, SearchHistory::class], version = 14)
abstract class TechShopDatabase : RoomDatabase() {
    abstract val wishListDao : WishListDao
    abstract val cartDao: CartDao
    abstract val searchDAO : SearchDAO
}