package com.vi.techshopmobile.presentation.filter

sealed class FilterEvent {
    object FilterProductByPrice : FilterEvent()
    object FilterProductsByBrand : FilterEvent()
    object FilterProductsByOption : FilterEvent()
}