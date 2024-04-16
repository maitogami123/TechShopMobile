package com.vi.techshopmobile.domain.repository.search

import arrow.core.Either
import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.domain.model.ErrorResponse
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun upsetArticle(searchHistory: SearchHistory)
    fun getSearchHistory(username: String): Flow<List<SearchHistory>>

}