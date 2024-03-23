package com.vi.techshopmobile.presentation.personal_info

import com.vi.techshopmobile.presentation.categories.CategoriesEvents

sealed class PersonalInfoEvent {

    data class GetAllEventPersonalInfo(val token: String): PersonalInfoEvent()
    data class GetCategoryProduct(val categoryName: String) : PersonalInfoEvent()
}