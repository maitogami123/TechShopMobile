package com.vi.techshopmobile.presentation.filter

import com.vi.techshopmobile.domain.model.ProductLine

data class FilterViewState(
    val products: List<ProductLine> = emptyList()
)

data class FilterLabel(
    val value: String,
    val label: String
)

data class FilterBrand(
    val brand: String,
    val products: List<ProductLine>
)
data class FilterPrice(
    val index: Int,
    val labelName: String,
    val valueStart: Double,
    val valueEnd: Double,
)