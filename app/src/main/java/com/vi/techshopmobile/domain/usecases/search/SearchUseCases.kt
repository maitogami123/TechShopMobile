package com.vi.techshopmobile.domain.usecases.search

import com.vi.techshopmobile.domain.usecases.products.GetSearchProducts

data class SearchUseCases(
    val upsetSearchHistory: UpsetSearchHistory,
    val getSearchHistory: GetSearchHistory,
)