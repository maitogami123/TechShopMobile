package com.vi.techshopmobile.presentation.product_details

import com.vi.techshopmobile.data.remote.authenticate.dto.SignInData
import com.vi.techshopmobile.presentation.authenticate.AuthenticateEvent

sealed class ProductDetailsEvent {
    data class GetDetailEvent(val productLine: String) : ProductDetailsEvent()

}