package com.vi.techshopmobile.presentation.products

sealed class ProductsEvents {
    object GetAllEventProduct : ProductsEvents()

    data class GetAllProductByCategory(val categoryName:String) : ProductsEvents()

    object GetAllProductByBrand : ProductsEvents()
}