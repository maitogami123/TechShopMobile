package com.vi.techshopmobile.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vi.techshopmobile.domain.model.CartItem
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cartItem: CartItem)

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("SELECT * FROM CartItem WHERE username=:username")
    fun getCart(username: String): Flow<List<CartItem>>
}