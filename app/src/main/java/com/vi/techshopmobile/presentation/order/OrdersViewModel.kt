package com.vi.techshopmobile.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.orders.OrdersUseCases
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersUseCases: OrdersUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(OrdersViewState())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun onEvent(event: OrdersEvents) {
        when (event) {
            is OrdersEvents.GetAllEvent -> {
                getOrders("Bearer " + event.token)
            }
            else -> {}
        }
    }

    fun getOrders(token: String){
        viewModelScope.launch {
            _isLoading.value = true
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            ordersUseCases.getOrders(token)
                .onRight { listOrder ->
                    _state.update {
                        it.copy(orders = listOrder )
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

    fun filterOrdersByStatus(status: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            val token = "your_token_here" // Thay token của bạn ở đây
            try {
                val orders = when (status) {
                    "Tất cả" -> ordersUseCases.getOrders(token)
                    "Đang xử lý" -> ordersUseCases.getOrders(token)
                    "Đã xử lý" -> ordersUseCases.getOrders(token)
                    "Đang giao hàng" -> ordersUseCases.getOrders(token)
                    "Hoàn thành" -> ordersUseCases.getOrders(token)
                    "Đã hủy" -> ordersUseCases.getOrders(token)
                    else -> ordersUseCases.getOrders(token)
                }
                _state.update {
                    it.copy(orders = it.orders)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = e.message)
                }
            } finally {
                _state.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

}