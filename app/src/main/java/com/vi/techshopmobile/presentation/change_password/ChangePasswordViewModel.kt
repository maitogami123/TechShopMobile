package com.vi.techshopmobile.presentation.change_password

import androidx.lifecycle.ViewModel
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor() : ViewModel(){
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


}


