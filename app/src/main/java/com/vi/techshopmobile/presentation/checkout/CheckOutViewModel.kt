package com.vi.techshopmobile.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.domain.usecases.orders.OrdersUseCases
import com.vi.techshopmobile.domain.usecases.userDetail.UserDetailsUseCases
import com.vi.techshopmobile.domain.usecases.vnpay.VnPayUseCases
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
    private val vnPayUseCases: VnPayUseCases
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

    private var _isUpdateUserDetail = MutableStateFlow(false)
    val isUpdateUserDetail = _isUpdateUserDetail.asStateFlow()

    private var _createUserDetailError = MutableStateFlow("");
    val createUserDetailError = _createUserDetailError.asStateFlow();

    private var _idOrderCreated = MutableStateFlow("")
    val idOrderCreated = _idOrderCreated.asStateFlow()

    private var _isOrderLoading = MutableStateFlow(false)
    val isOrderLoading = _isOrderLoading.asStateFlow()

    private var _paymentUrl = MutableStateFlow("")
    val paymentUrl = _paymentUrl.asStateFlow()

    private var _orderPaymentStatus = MutableStateFlow("PENDING");
    val orderPaymentStatus = _orderPaymentStatus.asStateFlow();

    private var _pollingRequest = MutableStateFlow(10);
    val pollingRequest = _pollingRequest.asStateFlow();

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

            is CheckOutEvent.GetUserDetailById -> {
                getUserDetailById("Bearer " + event.token, event.id)
            }

            is CheckOutEvent.CreateOrders -> {
                viewModelScope.launch {
                    _isOrderLoading.value = true
                    val orderResponse = ordersUseCases.createOrders(
                        token = "Bearer " + event.token,
                        requestCheckOut = event.requestCheckOut
                    )
                    if (orderResponse.isRight()) {
                        orderResponse.onRight {
                            sendEvent(Event.Toast("Tạo đơn hàng thành công"))
                            _idOrderCreated.value = it.id.toString()
                            _isCreateOrder.value = true
                            _isOrderLoading.value = false
                            _orderPaymentStatus.value = "SUCCESS"
                        }
                    } else {
                        orderResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                            _isCreateOrder.value = false
                            _isOrderLoading.value = false
                            _orderPaymentStatus.value = "FAIL"
                        }
                    }
                }
            }

            is CheckOutEvent.CreateUserDetail -> {
                viewModelScope.launch {
                    _isOrderLoading.value = true
                    val userDetailRes = userDetailsUseCases.createUserDetail(
                        token = "Bearer " + event.token,
                        userDetailRequest = event.userDetailRequest
                    )
                    if (userDetailRes.isRight()) {
                        userDetailRes.onRight {
                            sendEvent(Event.Toast("Tạo thông tin người dùng ${statePerson.value.userDetail.username} thành công"))
                            _isCreateUserDetail.value = true
                        }
                    } else {
                        userDetailRes.onLeft {
                            _createUserDetailError.value = it.detail
                            sendEvent(Event.Toast(it.detail + "Có lỗi xảy ra"))
                            _isOrderLoading.value = false
                        }
                    }
                }
            }

            is CheckOutEvent.UpdateAllUserDetailsToNotDefault -> {
                viewModelScope.launch {
                    val updateRes = userDetailsUseCases.updateAllUserDetailsToNotDefault(
                        id = event.id,
                        token = "Bearer " + event.token
                    )
                    if (updateRes.isRight()) {
                        updateRes.onRight {
                            sendEvent(Event.Toast("Đã đặt địa chỉ thành mặc định"))
                        }
                    } else {
                        updateRes.onLeft {
                            sendEvent(Event.Toast(it.detail + "Có lỗi xảy ra"))
                        }
                    }
                }
            }

            is CheckOutEvent.UpdateUserDetail -> {
                viewModelScope.launch {
                    val updateUserDetailResponse = userDetailsUseCases.updateUserDetail(
                        token = "Bearer " + event.token,
                        id = event.id,
                        updateUserDetailRequest = event.updateUserDetailRequest
                    )
                    if (updateUserDetailResponse.isRight()) {
                        updateUserDetailResponse.onRight {
                            sendEvent(Event.Toast("Cập nhật thông tin người dùng ${statePerson.value.userDetail.username} thành công"))
                            _isUpdateUserDetail.value = true
                        }
                    } else {
                        updateUserDetailResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                            _isUpdateUserDetail.value = false
                        }
                    }
                }
            }

            is CheckOutEvent.ClearCart -> {
                viewModelScope.launch {
                    cartUseCases.clearCart(event.username)
                    calculateTotal()
                }
            }

            is CheckOutEvent.VnPayPayment -> {
                viewModelScope.launch {
                    val orderVnPayResponse = vnPayUseCases.createOrderByVnpay(
                        token = "Bearer " + event.token,
                        requestCheckOut = event.requestCheckOut
                    )

                    if (orderVnPayResponse.isRight()) {
                        orderVnPayResponse.onRight {
                            sendEvent(Event.Toast("Vui lòng thanh toán để tạo đơn hàng"))
                            _idOrderCreated.value = it.orderId.toString()
                            // Add loading state
                            _isLoading.value = true
//                            _isCreateOrder.value = true
                            _paymentUrl.value = it.url
                        }
                    } else {
                        orderVnPayResponse.onLeft {
                            sendEvent(Event.Toast(it.detail))
                        }
                    }
                }
            }

            is CheckOutEvent.PollingOrderInfo -> {
                viewModelScope.launch {
                    _pollingRequest.value -= 1;
                    val orderResponse = ordersUseCases.getOrdersDetail("Bearer " + event.token, event.orderId);
                    if (orderResponse.isRight()) {
                        orderResponse.onRight {
                            if (it.paymentStatus == "SUCCESS") {
                                sendEvent(Event.Toast("Đơn hàng thanh toán thành công"))
                                _isLoading.value = false
                                _orderPaymentStatus.value = "SUCCESS";
                                _pollingRequest.value -= 0;
                            } else if (it.paymentStatus == "FAIL"){
                                _isLoading.value = false
                                _orderPaymentStatus.value = "FAIL";
                                _pollingRequest.value -= 0;
                            }
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

    fun getUserDetailById(token: String, id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _statePerson.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            userDetailsUseCases.getUserDetailById(token, id)
                .onRight { detailUserDetail ->
                    _statePerson.update {
                        it.copy(
                            detailUserDetail = detailUserDetail
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