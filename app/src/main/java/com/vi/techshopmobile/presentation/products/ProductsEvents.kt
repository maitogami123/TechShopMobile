package com.vi.techshopmobile.presentation.products

import com.vi.techshopmobile.domain.model.ProductLine

sealed class ProductsEvents {

    object GetAllEventProduct : ProductsEvents()

    data class GetAllProductByCategory(val categoryName:String) : ProductsEvents()

}