package com.vi.techshopmobile.domain.repository.search

import com.vi.techshopmobile.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchHistory(username:String):Flow<List<SearchHistory>>
}