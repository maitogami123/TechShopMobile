package com.vi.techshopmobile.presentation.search

import com.vi.techshopmobile.data.remote.products.dto.SearchProduct
import com.vi.techshopmobile.domain.model.SearchHistory

sealed class SearchEvent {
    data class GetUserSearchHistory(val username: String) : SearchEvent()

    data class AddSearchStringToList(val searchHistory: SearchHistory) : SearchEvent()

    data class GetProductSearch(val searchProduct: SearchProduct) : SearchEvent()
}