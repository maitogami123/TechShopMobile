package com.vi.techshopmobile.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vi.techshopmobile.domain.model.WishItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(wishItem: WishItem)

    @Delete
    suspend fun delete(wishItem: WishItem)

    @Query("SELECT * FROM WishItem WHERE username=:username")
    fun getWishList(username: String): Flow<List<WishItem>>
}