package com.vi.techshopmobile.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.model.WishItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(wishItem: WishItem)

    @Delete
    suspend fun delete(wishItem: WishItem)

    @Query("DELETE FROM WishItem WHERE username = :username and productLine = :productLine")
    suspend fun deleteItem(username: String, productLine: String)

    @Query("SELECT * FROM WishItem WHERE username=:username")
    fun getWishList(username: String): Flow<List<WishItem>>

    @Query("SELECT * FROM WishItem Where username=:username and productLine=:productLine")
    fun selectWishItem(username: String, productLine: String): Flow<WishItem>

    @Query("Update WishItem set isSelected=:isSelected Where id=:id")
    suspend fun updateWishItem(isSelected: Boolean, id: Int)
}