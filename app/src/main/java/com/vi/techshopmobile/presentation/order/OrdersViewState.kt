package com.vi.techshopmobile.presentation.order

import com.vi.techshopmobile.domain.model.OrderItem

data class OrdersViewState(
    val isLoading: Boolean = true,
    val orders: List<OrderItem> = emptyList(),
    val error: String? = null
){}