package com.vi.techshopmobile.presentation.checkout

import android.util.Log
import androidx.compose.runtime.MutableIntState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.domain.usecases.orders.OrdersUseCases
import com.vi.techshopmobile.domain.usecases.userDetail.UserDetailsUseCases
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewState
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
    private val userDetailsUseCases: UserDetailsUseCases,
    private val ordersUseCases: OrdersUseCases,
) : ViewModel() {
    private var _state = MutableStateFlow(emptyList<CartItem>())
    val state = _state.asStateFlow()

    private val _statePerson = MutableStateFlow(PersonalInfoViewState())
    val statePerson = _statePerson.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    private var _isCreateOrder = MutableStateFlow(false)
    val isCreateOrder = _isCreateOrder.asStateFlow()

    private var _isCreateUserDetail = MutableStateFlow(false)
    val isCreateUserDetail = _isCreateUserDetail.asStateFlow()

    private var _createUserDetailError = MutableStateFlow("");
    val createUserDetailError = _createUserDetailError.asStateFlow();
    fun onEvent(event: CheckOutEvent) {
        when (event) {
            is CheckOutEvent.GetUserCart -> {
                cartUseCases.getCart(event.username).onEach {
                    _state.value = it.reversed()
                    calculateTotal()
                }.launchIn(viewModelScope)
            }

            is CheckOutEvent.GetAllEventPersonalInfo -> {
                getUserDetail("Bearer " + event.token)
            }

            is CheckOutEvent.GetListUserDetail -> {
                getListUserDetail("Bearer " + event.token)
            }

            is CheckOutEvent.CreateOrders -> {
                viewModelScope.launch {
                    val orderResponse = ordersUseCases.createOrders(
                        token = "Bearer " + event.token,
                        requestCheckOut = event.requestCheckOut
                    )
                    if (orderResponse.isRight()) {
                        orderResponse.onRight {
                            sendEvent(Event.Toast("Tạo đơn hàng thành công"))
                            delay(600)
                            _isCreateOrder.value = true
                        }
                    } else {
                        orderResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                        }
                    }
                }
            }

            is CheckOutEvent.CreateUserDetail -> {
                viewModelScope.launch {
                    val userDetailRes = userDetailsUseCases.createUserDetail(
                        token = "Bearer " + event.token,
                        userDetailRequest = event.userDetailRequest
                    )
                    if (userDetailRes.isRight()) {
                        userDetailRes.onRight {
                            sendEvent(Event.Toast("Tạo thông tin người dùng ${statePerson.value.userDetail.username} thành công"))
                            delay(600)
                            _isCreateUserDetail.value = true
                        }
                    } else {
                        userDetailRes.onLeft {
                            _createUserDetailError.value = it.detail
                            sendEvent(Event.Toast(it.detail + "Có lỗi xảy ra"))
                        }
                    }
                }
            }
        }
    }

    fun getUserDetail(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _statePerson.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            userDetailsUseCases.getUserDetails(token)
                .onRight { userDetail ->
                    _statePerson.update {
                        it.copy(
                            userDetail = userDetail
                        )
                    }

                }
                .onLeft { error ->
                    _statePerson.update {
                        it.copy(
                            error = error.detail
                        )
                    }
                    EventBus.sendEvent(Event.Toast(error.detail))
                }

            _statePerson.update {
                it.copy(isLoading = false)
            }
            _isLoading.value = false
        }
    }

    fun getListUserDetail(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _statePerson.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            userDetailsUseCases.getListUserDetail(token)
                .onRight { listUserDetail ->
                    _statePerson.update {
                        it.copy(
                            listUserDetail = listUserDetail
                        )
                    }

                }
                .onLeft { error ->
                    _statePerson.update {
                        it.copy(
                            error = error.detail
                        )
                    }
                    EventBus.sendEvent(Event.Toast(error.detail))
                }

            _statePerson.update {
                it.copy(isLoading = false)
            }
            _isLoading.value = false
        }
    }

    fun calculateTotal() {
        var totalPrice = 0.0
        _state.value.forEach {
            totalPrice += (it.price * it.quantity!!)
        }
        _totalPrice.value = totalPrice
    }
}