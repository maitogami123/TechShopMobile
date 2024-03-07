package com.vi.techshopmobile.presentation.home_navigator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.techshopmobile.domain.usecases.app_session.AppSessionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TechShopNavigatorViewModel @Inject constructor(
    private val appSessionUseCases: AppSessionUseCases
) : ViewModel() {
    var accessToken by mutableStateOf("")
    init {
        appSessionUseCases.readSession().onEach {
            accessToken = it
        }.launchIn(viewModelScope)
    }
}