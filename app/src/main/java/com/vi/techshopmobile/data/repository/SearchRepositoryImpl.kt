package com.vi.techshopmobile.data.repository

import com.vi.techshopmobile.data.local.SearchDao
import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchHistoryDAO: SearchDao
) :
    SearchRepository {

    override suspend fun upsetArticle(searchHistory: SearchHistory) {
        return searchHistoryDAO.upsert(searchHistory)
    }

    override fun getSearchHistory(username: String): Flow<List<SearchHistory>> {
        return searchHistoryDAO.getSearchHistory(username)
    }

}