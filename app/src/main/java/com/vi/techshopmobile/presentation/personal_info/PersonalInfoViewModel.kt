package com.vi.techshopmobile.presentation.personal_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import com.vi.techshopmobile.domain.usecases.authenticate.AuthenticateUseCases
import com.vi.techshopmobile.domain.usecases.userDetail.UserDetailsUseCases
import com.vi.techshopmobile.presentation.categories.CategoriesEvents
import com.vi.techshopmobile.presentation.categories.CategoriesViewState
import com.vi.techshopmobile.presentation.user_setting.UserSettingEvent
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(
    private val userDetailsUseCases: UserDetailsUseCases,
) : ViewModel(){

    private val _state = MutableStateFlow(PersonalInfoViewState())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun onEvent(event: PersonalInfoEvent) {
        when (event) {
            is PersonalInfoEvent.GetAllEventPersonalInfo -> {
                getUserDetail( "Bearer " +event.token)
            }
        }
    }
    fun getUserDetail(token: String){
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

}