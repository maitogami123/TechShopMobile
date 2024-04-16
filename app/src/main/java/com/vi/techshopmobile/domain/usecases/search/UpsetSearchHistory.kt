package com.vi.techshopmobile.domain.usecases.search

import com.vi.techshopmobile.domain.model.SearchHistory
import com.vi.techshopmobile.domain.repository.search.SearchRepository

class UpsetSearchHistory(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(searchHistory: SearchHistory) {
        searchRepository.upsetArticle(searchHistory)
    }
}