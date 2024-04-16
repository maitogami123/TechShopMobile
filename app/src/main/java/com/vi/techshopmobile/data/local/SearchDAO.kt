package com.vi.techshopmobile.data.local;

import androidx.room.Dao;
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query;
import com.vi.techshopmobile.domain.model.SearchHistory;
import kotlinx.coroutines.flow.Flow;

@Dao
interface SearchDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(searchHistory: SearchHistory)

    @Query("SELECT * FROM SearchHistory WHERE username=:username")
    fun getSearchHistory(username: String): Flow<List<SearchHistory>>

}