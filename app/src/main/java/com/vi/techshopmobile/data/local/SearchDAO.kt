package com.vi.techshopmobile.data.local;

import androidx.room.Dao;
import androidx.room.Query;
import com.vi.techshopmobile.domain.model.SearchHistory;
import kotlinx.coroutines.flow.Flow;

@Dao
interface SearchDAO {
    @Query("SELECT * FROM SearchHistory WHERE username=:username")
    fun getSearchHistory(username: String): Flow<List<SearchHistory>>

}