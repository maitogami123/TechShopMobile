package com.vi.techshopmobile.domain.usecases.orders

data class OrdersUseCases(
    val getOrders: GetOrders,
    val getOrdersDetail: GetOrdersDetail,
    val createOrders: CreateOrders
)