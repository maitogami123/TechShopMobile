package com.vi.techshopmobile.presentation.order_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.OrderItem
import com.vi.techshopmobile.domain.usecases.orders.OrdersUseCases
import com.vi.techshopmobile.domain.usecases.userDetail.UserDetailsUseCases
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoEvent
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewState
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val userDetailsUseCases: UserDetailsUseCases,
    private val ordersUseCases: OrdersUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(OrderDetailsViewState())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

//    private val _selectedOrder = MutableStateFlow<OrderItem?>(null)
//    val selectedOrder: StateFlow<OrderItem?> = _selectedOrder
//
//    fun selectOrder(order: OrderItem) {
//        _selectedOrder.value = order
//    }

    fun onEvent(event: OrderDetailsEvent) {
        when (event) {
            is OrderDetailsEvent.GetAllEventPersonalInfo -> TODO()
            is OrderDetailsEvent.GetOrderDetail -> getOrderDetail(event.token, event.id)
        }
    }

    fun getUserDetail(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            userDetailsUseCases.getUserDetails(token)
                .onRight { userDetail ->
                    _state.update {
                        it.copy(
                            userDetail = userDetail
                        )
                    }

                }
                .onLeft { error ->
                    _state.update {
                        it.copy(
                            error = error.detail
                        )
                    }
                    EventBus.sendEvent(Event.Toast(error.detail))
                }

            _state.update {
                it.copy(isLoading = false)
            }
            _isLoading.value = false
        }
    }

    fun getOrderDetail(token: String, id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            ordersUseCases.getOrdersDetail(token, id)
                .onRight { orderDetail ->
                    _state.update {
                        it.copy(
                            orderDetailResponse = orderDetail
                        )
                    }
                    calculateTotal()
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(
                            error = error.detail
                        )
                    }
                    EventBus.sendEvent(Event.Toast(error.detail))
                }

            _state.update {
                it.copy(isLoading = false)
            }
            _isLoading.value = false
        }
    }

    fun calculateTotal() {
        var totalPrice = 0.0
        _state.value.orderDetailResponse!!.orderItems.forEach {
            totalPrice += it.price
        }
        _totalPrice.value = totalPrice
    }
}