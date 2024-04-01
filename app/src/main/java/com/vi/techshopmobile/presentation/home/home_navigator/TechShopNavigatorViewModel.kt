package com.vi.techshopmobile.presentation.home.home_navigator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.domain.model.CartItem
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.domain.usecases.cart.CartUseCases
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import com.vi.techshopmobile.util.decodeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Clock
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TechShopNavigatorViewModel @Inject constructor(
    private val appSessionUseCases: AppSessionUseCases,
) : ViewModel() {
    var accessToken by mutableStateOf("")

    init {
        appSessionUseCases.readSession().onEach {
            accessToken = it
        }.launchIn(viewModelScope)
        if (accessToken.isNotEmpty()){
            checkSession(accessToken)
        }
    }

    fun checkSession(token: String) {
        val currentTime = System.currentTimeMillis() / 1000
        if (currentTime > decodeToken(token).exp) {
            viewModelScope.launch {
                appSessionUseCases.deleteSession()
                EventBus.sendEvent(Event.Toast("Phiên đăng nhập hết hạn"))
            }
        }
    }
}