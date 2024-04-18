package com.vi.techshopmobile.presentation.filter

import com.vi.techshopmobile.domain.model.ProductLine

sealed class FilterEvent {
    data class GetProducts (val products: List<ProductLine>): FilterEvent()
    data class FilterProductByPrice(
        val index: List<Int>,
        val products: List<ProductLine>,
        val start: Double,
        val end: Double
    ) :
        FilterEvent()

    data class FilterProductsByBrand(
        val index: List<Int>,
        val products: List<ProductLine>,
        val value: String
    ) :
        FilterEvent()

    data class FilterProductsByOption(
        val index: Int,
        val products: List<ProductLine>,
        val value: String
    ) :
        FilterEvent()
}