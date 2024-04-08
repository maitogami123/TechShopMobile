package com.vi.techshopmobile.data.repository

import com.vi.techshopmobile.data.local.SearchDAO
import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchHistoryDAO: SearchDAO):SearchRepository {
    override fun getSearchHistory(username: String): Flow<List<SearchHistory>> {
        TODO("Not yet implemented")
    }
}