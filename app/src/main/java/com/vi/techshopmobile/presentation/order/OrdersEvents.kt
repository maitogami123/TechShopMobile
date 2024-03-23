package com.vi.techshopmobile.presentation.order

sealed class OrdersEvents {

    data class  GetAllEvent(val token: String): OrdersEvents()

}