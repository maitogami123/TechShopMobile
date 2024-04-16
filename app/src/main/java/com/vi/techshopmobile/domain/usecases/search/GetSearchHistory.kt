package com.vi.techshopmobile.domain.usecases.search

import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.domain.repository.search.SearchRepository

import kotlinx.coroutines.flow.Flow

class GetSearchHistory (
    private val searchRepository: SearchRepository
) {
     operator fun invoke(username: String): Flow<List<SearchHistory>> {
        return searchRepository.getSearchHistory(username)
    }
}