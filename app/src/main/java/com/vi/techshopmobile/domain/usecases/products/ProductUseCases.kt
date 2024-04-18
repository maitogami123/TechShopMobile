package com.vi.techshopmobile.domain.usecases.products

import com.vi.techshopmobile.domain.usecases.search.GetSearchHistory

data class ProductUseCases(
    val getProducts: GetProducts,
    val getProductDetail: GetProductDetail,
    val getSearchProducts: GetSearchProducts,
    val getProductsRandom: GetProductsRandom
)